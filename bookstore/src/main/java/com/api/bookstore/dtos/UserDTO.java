package com.api.bookstore.dtos;

import com.api.bookstore.entities.Role;
import com.api.bookstore.entities.User;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;

    public UserDTO(User user){
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        role = user.getRole();
    }

    public UserDTO(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDTO(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
