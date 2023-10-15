package com.luizreis.blogspring.dtos.auth;

public class TokenDTO {

    private Long id;
    private String token;

    public TokenDTO(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
