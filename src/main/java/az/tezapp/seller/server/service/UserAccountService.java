package az.tezapp.seller.server.service;

import az.tezapp.seller.server.domain.Account;
import az.tezapp.seller.server.model.dto.UserDto;

public interface UserAccountService {

    UserDto saveUserAccount(Account account);

}
