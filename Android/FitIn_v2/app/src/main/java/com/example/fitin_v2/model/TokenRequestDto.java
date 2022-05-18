package com.example.fitin_v2.model;

public class TokenRequestDto {

    private String accessToken;
    private String refreshToken;


    public TokenRequestDto(String access, String refresh) {
        this.accessToken = access;
        this.refreshToken = refresh;
    }
}
