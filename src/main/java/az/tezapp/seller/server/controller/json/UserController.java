package az.tezapp.seller.server.controller.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import az.tezapp.seller.server.domain.Account;
import az.tezapp.seller.server.exception.KeyAccessDeniedException;
import az.tezapp.seller.server.manager.SecurityManager;
import az.tezapp.seller.server.model.dto.UserDto;
import az.tezapp.seller.server.service.UserAccountService;

@RestController
@RequestMapping(value = "/users")
public class UserController {	
	
	@Autowired
	private UserAccountService userService;
		
	@RequestMapping(method = RequestMethod.POST)
	public UserDto regUser(@RequestBody Account account) throws KeyAccessDeniedException{					
		account.setEmail(SecurityManager.getValid(account.getEmail()));
		UserDto userDto = userService.saveUserAccount(account);
		return userDto;
	}
}
