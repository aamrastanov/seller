package az.tezapp.seller.server.domain.repository;

import org.springframework.data.repository.CrudRepository;

import az.tezapp.seller.server.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findFirstByUsername(String username);

}
