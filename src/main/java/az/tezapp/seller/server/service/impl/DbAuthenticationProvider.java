package az.tezapp.seller.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.stereotype.Service;

import az.tezapp.seller.server.Application;
import az.tezapp.seller.server.domain.User;
import az.tezapp.seller.server.domain.repository.UserRepository;
import az.tezapp.seller.server.manager.LoggerManager;;

@Service
public class DbAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private UserRepository userRepository;

    private static String errorMessage = "Access denied by " + DbAuthenticationProvider.class.getName();

    @Autowired
    public DbAuthenticationProvider(UserRepository userRepository, CacheManager cacheManager) {
        super();
        this.userRepository = userRepository;
        try {
            setUserCache(new SpringCacheBasedUserCache(cacheManager.getCache(Application.userCacheName)));
        } catch (Exception e) {
            LoggerManager.warn("Continue without user cache", e);
        }
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (!authentication.getCredentials().equals(userDetails.getPassword())) {
            throw new BadCredentialsException(errorMessage);
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        User user = userRepository.findFirstByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(errorMessage);
        }
        return user;
    }

}
