package com.luizreis.blogspring.dtos.auth;

public class SignupDTO {

    private String username;
    private String password;
    private String email;

    public SignupDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
