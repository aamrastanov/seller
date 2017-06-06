package az.tezapp.seller.server.controller.json;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import az.tezapp.seller.server.domain.Item;
import az.tezapp.seller.server.domain.User;
import az.tezapp.seller.server.domain.repository.ItemRepository;

@RestController
@RequestMapping(value = "/items")
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepository;

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public Long addOrUpdate(@RequestBody Item item,  Authentication authentication){
		User currentUser = (User)authentication.getPrincipal();		
		item.setUser(currentUser);
		item.setCreateDate(new Date());
		itemRepository.save(item);
		return item.getId();			
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/own", method = RequestMethod.GET)
	public List<Item> getAllOwn(Authentication authentication){			
		/*User currentUser = (User)authentication.getPrincipal();
		return itemRepository.findByAccountIn(currentUser.getAccounts());*/
		return null;
	}
	
}
