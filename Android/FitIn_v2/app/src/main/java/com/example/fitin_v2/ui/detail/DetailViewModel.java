package com.example.fitin_v2.ui.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fitin_v2.model.NewsListResponseDto;
import com.example.fitin_v2.model.NewsResponseDto;
import com.example.fitin_v2.repository.NewsRepository;

import io.reactivex.disposables.CompositeDisposable;

public class DetailViewModel extends AndroidViewModel {

    private NewsRepository newsRepository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<NewsListResponseDto> _selectNews = new MutableLiveData<>();
    LiveData<NewsListResponseDto> news;

    private final LiveData<NewsResponseDto> newsDetail;

    public DetailViewModel(NewsListResponseDto newsListResponseDto, @NonNull Application application) {
        super(application);

        newsRepository = new NewsRepository(application);
        _selectNews.setValue(newsListResponseDto);
        news = _selectNews;

        Long newsId = news.getValue().getId();
        newsDetail = newsRepository.getNewsDetail(newsId);
    }

    public LiveData<NewsResponseDto> getNewsDetail() {
        return newsDetail;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        disposable.clear();
    }
}
