package com.example.fitin_v1.remote.api;

import com.google.gson.annotations.SerializedName;

public class AccountRequestDto {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    public AccountRequestDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

}