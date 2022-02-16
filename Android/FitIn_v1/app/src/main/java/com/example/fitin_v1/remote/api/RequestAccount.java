package com.example.fitin_v1.remote.api;

import com.google.gson.annotations.SerializedName;

public class RequestAccount {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    public RequestAccount(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Getter, Setter는 생략, 왜냐하면 EditText로 받은 값을 그대로 객체에 넘겨줄거라 생성자만 가지고도 처리가능함
    // 그리고 로그인의 경우 해당 UI/UX상 Response가 없어서 생략, 추후 논의할 예정


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}