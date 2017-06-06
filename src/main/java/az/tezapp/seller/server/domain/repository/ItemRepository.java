package az.tezapp.seller.server.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import az.tezapp.seller.server.domain.Item;
import az.tezapp.seller.server.domain.User;

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findByUser(User user);

}
