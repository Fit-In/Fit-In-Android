package com.example.fitin_v1.dto;

import com.google.gson.annotations.SerializedName;

public class AccountLoginDto {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public AccountLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
