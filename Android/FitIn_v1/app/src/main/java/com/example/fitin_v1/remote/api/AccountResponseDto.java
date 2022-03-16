package com.example.fitin_v1.remote.api;

import com.google.gson.annotations.SerializedName;

public class AccountResponseDto {

    @SerializedName("email")
    private String email;

    public String getEmail() {
        return email;
    }
}
