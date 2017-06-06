package az.tezapp.seller.server.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import az.tezapp.seller.server.Application;
import az.tezapp.seller.server.domain.Account;
import az.tezapp.seller.server.domain.User;
import az.tezapp.seller.server.domain.repository.AccountRepository;
import az.tezapp.seller.server.domain.repository.UserRepository;
import az.tezapp.seller.server.model.dto.UserDto;
import az.tezapp.seller.server.service.UserAccountService;

@Component
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public UserDto saveUserAccount(Account account) {
        String userName = getUserName(account);
        User user = userRepository.findFirstByUsername(userName);
        if (user == null) {
            user = new User();
            user.setUsername(userName);
            user.setPassword(generatePassword());
            userRepository.save(user);
            account.setUser(user);
            accountRepository.save(account);
        } else {
            cacheManager.getCache(Application.userCacheName).evict(user.getUsername());
            Account entityAcount = user.getAccount();
            entityAcount.update(account);
            accountRepository.save(entityAcount);
        }
        return new UserDto(user);
    }

    private String getUserName(Account account) {
        return account.getAccountType().toString() + "_" + account.getEmail();
    }

    private String generatePassword() {
        return UUID.randomUUID().toString();
    }

}
