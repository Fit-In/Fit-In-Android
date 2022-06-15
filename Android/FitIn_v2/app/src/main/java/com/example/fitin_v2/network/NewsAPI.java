package com.example.fitin_v2.network;

import com.example.fitin_v2.model.NewsListResponseDto;
import com.example.fitin_v2.model.NewsResponseDto;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsAPI {

    @GET("/news/save")
    Single<Void> callNews();

    @GET("/news")
    Single<List<NewsListResponseDto>> getNews();

    @GET("/news/{id}")
    Single<NewsResponseDto> getNewsDetail(@Path("id") Long id);

}
