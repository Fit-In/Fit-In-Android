package com.example.fitin_v2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NewsListResponseDto implements Parcelable {

    @SerializedName("id")
    private Long id;

    @SerializedName("press")
    private String press;

    @SerializedName("title")
    private String title;

    @SerializedName("category")
    private String category;

    public NewsListResponseDto(Long id, String press, String title, String category) {
        this.id = id;
        this.press = press;
        this.title = title;
        this.category = category;
    }

    protected NewsListResponseDto(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        press = in.readString();
        title = in.readString();
        category = in.readString();
    }

    public static final Creator<NewsListResponseDto> CREATOR = new Creator<NewsListResponseDto>() {
        @Override
        public NewsListResponseDto createFromParcel(Parcel in) {
            return new NewsListResponseDto(in);
        }

        @Override
        public NewsListResponseDto[] newArray(int size) {
            return new NewsListResponseDto[size];
        }
    };

    public Long getId() {
        return id;
    }

    public String getPress() {
        return press;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(press);
        parcel.writeString(title);
        parcel.writeString(category);
    }
}
