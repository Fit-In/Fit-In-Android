package com.example.fitin_v2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AccountRequestDto implements Parcelable {

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

    protected AccountRequestDto(Parcel in) {
        email = in.readString();
        password = in.readString();
        name = in.readString();
    }

    public static final Creator<AccountRequestDto> CREATOR = new Creator<AccountRequestDto>() {
        @Override
        public AccountRequestDto createFromParcel(Parcel in) {
            return new AccountRequestDto(in);
        }

        @Override
        public AccountRequestDto[] newArray(int size) {
            return new AccountRequestDto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(name);
    }
}
