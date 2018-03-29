package az.tezapp.seller.server.controller.rest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import az.tezapp.seller.server.controller.BaseControllerTest;
import az.tezapp.seller.server.domain.Item;
import az.tezapp.seller.server.domain.ItemImage;
import az.tezapp.seller.server.domain.User;
import az.tezapp.seller.server.domain.repository.ItemRepository;
import az.tezapp.seller.server.domain.repository.UserRepository;
import az.tezapp.seller.server.exception.ControllerLogicException;
import az.tezapp.seller.server.manager.ResourcesManager;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
@WebAppConfiguration
public class ItemImageControllerTest extends BaseControllerTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourcesManager resourcesManager;

    private Item item;

    private File file;

    private User user;

    @Before
    public void before() {
        initMockMvc();
        user = userRepository.findFirstByUsername("admin");
        item = new Item();
        item.setPrice(1f);
        item.setDescription("test");
        item.setCreateDate(new Date());
        itemRepository.save(item);
    }

    @After
    public void after() {
        itemRepository.delete(item);
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    @Test
    public void uploadImage() throws UnsupportedEncodingException, Exception {
        item.setUser(user);
        itemRepository.save(item);
        MockMultipartFile fileMock = new MockMultipartFile("file", "file.jpg", "iamge/jpeg", new byte[] { 1, 2, 3 });
        String response = mvc
                .perform(fileUpload("/items/" + item.getId() + "/images").file(fileMock)
                        .with(httpBasic("admin", "admin123")))
                .andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8)).andReturn()
                .getResponse().getContentAsString();
        ItemImage itemImage = jsonStringToObject(response, ItemImage.class);
        assertNotNull(itemImage);
        assertNotNull(itemImage.getId());
        assertNotNull(itemImage.getFileUri());
        file = new File(resourcesManager.getLocalResoucesPath() + itemImage.getFileUri());
        assertTrue("File not exists", file.exists());
    }

    @Test
    public void uploadImageAccessDenied() throws UnsupportedEncodingException, Exception {
        try {
            MockMultipartFile fileMock = new MockMultipartFile("file", "file.jpg", "iamge/jpeg",
                    new byte[] { 1, 2, 3 });
            mvc.perform(fileUpload("/items/" + item.getId() + "/images").file(fileMock)
                    .with(httpBasic("admin", "admin123"))).andExpect(status().isUnprocessableEntity());
        } catch (ControllerLogicException e) {
        }
    }

    @Test
    public void deleteImage() throws UnsupportedEncodingException, Exception {

    }
}
