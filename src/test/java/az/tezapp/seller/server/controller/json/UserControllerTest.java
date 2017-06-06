package az.tezapp.seller.server.controller.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.sql.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

import az.tezapp.seller.server.Application;
import az.tezapp.seller.server.controller.BaseControllerTest;
import az.tezapp.seller.server.domain.Account;
import az.tezapp.seller.server.domain.User;
import az.tezapp.seller.server.domain.repository.AccountRepository;
import az.tezapp.seller.server.domain.repository.UserRepository;
import az.tezapp.seller.server.manager.AESAlghorithm;
import az.tezapp.seller.server.model.AccountType;
import az.tezapp.seller.server.model.Gender;
import az.tezapp.seller.server.model.dto.UserDto;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0", "management.port=0"})
public class UserControllerTest extends BaseControllerTest{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	private User user;
	
	private Account account;
	
	@Before
	public void before(){
		initMockMvc();
	}
	
	@After
	public void after(){
		if (account != null){
			accountRepository.delete(account);
		}
		if (user != null){
			userRepository.delete(user);
		}
	}
	
	@Test
	public void regUser() throws UnsupportedEncodingException, JsonProcessingException, Exception{		
		Account testAccount = new Account();
		testAccount.setFirstName("Test");
		testAccount.setLastName("Testov");
		testAccount.setBirthday(Date.valueOf("2012-6-1"));
		testAccount.setGender(Gender.valueOf("M"));
		testAccount.setPhoto("TestPhoto");
		testAccount.setPhone("9009009090");
		testAccount.setUrl("example.com");
		testAccount.setAccountType(AccountType.valueOf("google"));
		String originalEmail = "test@mail.ru";
		testAccount.setEmail(AESAlghorithm.encrypt("electrika"+originalEmail, "4#lk09_fg5s345k7"));		
		String content = mvc
    		.perform(post("/users")        				        				
				.contentType(APPLICATION_JSON_UTF8)
				.content(objectToJsonString(testAccount))    				
    		)
	    	.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andReturn().getResponse().getContentAsString();
		UserDto userDto = jsonStringToObject(content, UserDto.class);
		Assert.assertEquals(userDto.getName(), testAccount.getAccountType().toString() + "_" + originalEmail);
		Assert.assertTrue(!StringUtils.isEmpty(userDto.getToken()));
		user = userRepository.findFirstByUsername(userDto.getName());
		assertNotNull(user);
		account = user.getAccount();
		assertNotNull(account);
		assertEquals(testAccount.getFirstName(), account.getFirstName());
		assertEquals(testAccount.getLastName(), account.getLastName());
		userRepository.delete(user);
		user = userRepository.findOne(user.getId());
		assertNull(user);
		account = accountRepository.findOne(account.getId());
		assertNull(account);
	}
	
	@Test
	public void updateUser() throws UnsupportedEncodingException, JsonProcessingException, Exception{
		user = new User();
		user.setUsername("google_test@test.ru");
		user.setPassword("test");
		userRepository.save(user);
		account = new Account();
		account.setFirstName("test");
		account.setLastName("testov");
		account.setEmail("test@test.ru");
		account.setAccountType(AccountType.google);
		account.setUrl("example.com");
		account.setUser(user);
		accountRepository.save(account);		
		
		Account testAccount = new Account();
		testAccount.setFirstName("test2");
		testAccount.setLastName("testov2");
		testAccount.setUrl("example2.com");
		testAccount.setAccountType(AccountType.google);
		testAccount.setEmail(AESAlghorithm.encrypt("electrikatest@test.ru", "4#lk09_fg5s345k7"));
		
		String content = mvc
	    		.perform(post("/users")        				        				
					.contentType(APPLICATION_JSON_UTF8)
					.content(objectToJsonString(testAccount))    				
	    		)
		    	.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andReturn().getResponse().getContentAsString();
		UserDto userDto = jsonStringToObject(content, UserDto.class);
		assertEquals(userDto.getName(), user.getUsername());
		assertEquals(userDto.getToken(), user.getPassword());
		user = userRepository.findOne(user.getId());
		assertEquals(user.getAccount().getFirstName(), testAccount.getFirstName());
		assertEquals(user.getAccount().getLastName(), testAccount.getLastName());
		assertEquals(user.getAccount().getUrl(), testAccount.getUrl());
	}
}
