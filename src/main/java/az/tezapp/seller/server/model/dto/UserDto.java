package az.tezapp.seller.server.model.dto;

import az.tezapp.seller.server.domain.User;

public class UserDto {

    private String token;

    private String name;

    public UserDto() {

    }

    public UserDto(User user) {
        this.name = user.getUsername();
        this.token = user.getPassword();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
