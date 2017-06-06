package az.tezapp.seller.server.domain.repository;

import org.springframework.data.repository.CrudRepository;

import az.tezapp.seller.server.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

}
