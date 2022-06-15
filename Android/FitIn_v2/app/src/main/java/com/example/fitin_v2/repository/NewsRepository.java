package com.example.fitin_v2.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fitin_v2.model.NewsListResponseDto;
import com.example.fitin_v2.model.NewsResponseDto;
import com.example.fitin_v2.network.NewsAPI;
import com.example.fitin_v2.network.RetrofitBuilder;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("LogNotTimber")
public class NewsRepository {

    private final NewsAPI newsApi;
    private final CompositeDisposable disposable = new CompositeDisposable();

    public NewsRepository(Application application) {
        newsApi = RetrofitBuilder.getRetrofit().create(NewsAPI.class);
    }

    public void callNews() {
        disposable.add(newsApi.callNews()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
           success -> {
               Log.e("성공", "성공: " + success);
           }, error -> {
               Log.e("실패", "실패: " + error);
                }
        ));
    }

    public LiveData<List<NewsListResponseDto>> getNews() {
        MutableLiveData<List<NewsListResponseDto>> news = new MutableLiveData<>();

        disposable.add(newsApi.getNews()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
           newsList -> {
                news.setValue(newsList);
           }, error -> {
                news.setValue(null);
                }
        ));

        return news;
    }

    public LiveData<NewsResponseDto> getNewsDetail(Long id) {
        MutableLiveData<NewsResponseDto> newsDetail = new MutableLiveData<>();

        disposable.add(newsApi.getNewsDetail(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                success -> {
                    newsDetail.setValue(success);
                }, error -> {
                    newsDetail.setValue(null);
                }
        ));

        return newsDetail;
    }
}
