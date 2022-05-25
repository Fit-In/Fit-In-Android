package com.example.fitin_v2.ui.onboard.webview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class WebViewViewModelFactory implements ViewModelProvider.Factory {

    private final String url;
    private final Application application;

    public WebViewViewModelFactory(String url, Application application) {
        this.url = url;
        this.application = application;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        if (aClass.isAssignableFrom(WebViewViewModel.class)) {
            return (T) new WebViewViewModel(this.url, this.application);
        }
        throw new IllegalArgumentException("Unknown Viewmodel Class: " + aClass.getName());
    }
}
