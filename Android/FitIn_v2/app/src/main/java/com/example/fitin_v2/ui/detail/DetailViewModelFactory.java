package com.example.fitin_v2.ui.detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitin_v2.model.NewsListResponseDto;

public class DetailViewModelFactory implements ViewModelProvider.Factory {

    private final NewsListResponseDto newsListResponseDto;
    private final Application application;

    public DetailViewModelFactory(NewsListResponseDto newsListResponseDto, Application application) {
        this.newsListResponseDto = newsListResponseDto;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        if (aClass.isAssignableFrom(DetailViewModel.class)) {
            return (T) new DetailViewModel(this.newsListResponseDto, this.application);
        }
        throw new IllegalArgumentException("Unknown Viewmodel Class: " + aClass.getName());
    }
}
