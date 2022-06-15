package com.example.fitin_v2.ui.home;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fitin_v2.model.NewsListResponseDto;
import com.example.fitin_v2.repository.NewsRepository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class HomeViewModel extends AndroidViewModel {

    private NewsRepository newsRepository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final LiveData<List<NewsListResponseDto>> news;
    private MutableLiveData<NewsListResponseDto> _selectNews = new MutableLiveData<>();
    LiveData<NewsListResponseDto> selectNews;


    public HomeViewModel(@NonNull Application application) {
        super(application);

        newsRepository = new NewsRepository(application);
        news = newsRepository.getNews();
    }

    public LiveData<List<NewsListResponseDto>> getNewsList() {
        return news;
    }

    public void displayNews(NewsListResponseDto newsListResponseDto) {
        _selectNews.setValue(newsListResponseDto);
    }

    public void displayNewsFinish() {
        _selectNews.setValue(null);
    }

    public LiveData<NewsListResponseDto> getSelectNews() {
        return selectNews = _selectNews;
    }


    @Override
    protected void onCleared() {
        super.onCleared();

        disposable.clear();
    }
}
