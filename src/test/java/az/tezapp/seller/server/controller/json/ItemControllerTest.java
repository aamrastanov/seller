package az.tezapp.seller.server.controller.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;

import az.tezapp.seller.server.Application;
import az.tezapp.seller.server.controller.BaseControllerTest;
import az.tezapp.seller.server.domain.Item;
import az.tezapp.seller.server.domain.repository.ItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port:0", "management.port=0" })
public class ItemControllerTest extends BaseControllerTest {

    @Autowired
    private ItemRepository itemRepository;

    private Item item;

    @Before
    public void before() {
        initMockMvc();
    }

    @After
    public void after() {
        if (item != null) {
            itemRepository.delete(item);
        }
    }

    @Test
    public void addItem() throws UnsupportedEncodingException, JsonProcessingException, Exception {
        Item testItem = new Item();
        testItem.setDescription("test item");
        testItem.setPrice(3f);
        String response = mvc
                .perform(post("/items").with(httpBasic("admin", "admin123"))
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectToJsonString(testItem)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8)).andReturn()
                .getResponse().getContentAsString();
        item = itemRepository.findOne(Long.parseLong(response));
        assertNotNull(item);
        assertEquals(item.getPrice(), testItem.getPrice());
        assertEquals(item.getDescription(), testItem.getDescription());
    }

}
