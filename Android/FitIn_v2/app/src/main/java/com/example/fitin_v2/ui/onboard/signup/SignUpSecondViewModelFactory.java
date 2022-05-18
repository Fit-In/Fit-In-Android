package com.example.fitin_v2.ui.onboard.signup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitin_v2.model.AccountRequestDto;

public class SignUpSecondViewModelFactory implements ViewModelProvider.Factory {
    private final AccountRequestDto accountRequestDto;
    private final Application application;

    public SignUpSecondViewModelFactory(AccountRequestDto accountRequestDto, Application application) {
        this.accountRequestDto = accountRequestDto;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        if (aClass.isAssignableFrom(SignUpSecondViewModel.class)) {
            return (T) new SignUpSecondViewModel(this.accountRequestDto, this.application);
        }
        throw new IllegalArgumentException("Unknown Viewmodel Class: " + aClass.getName());
    }
}
