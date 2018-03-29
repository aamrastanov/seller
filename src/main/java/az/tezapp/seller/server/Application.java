package az.tezapp.seller.server;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import az.tezapp.seller.server.domain.User;
import az.tezapp.seller.server.domain.repository.UserRepository;

@SpringBootApplication
public class Application {

    public static final String userCacheName = "user";

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    public void initAdmin() {
        User user = userRepository.findFirstByUsername("admin");
        if (user == null) {
            user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            userRepository.save(user);
        }
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache(userCacheName)));
        return cacheManager;
    }

}