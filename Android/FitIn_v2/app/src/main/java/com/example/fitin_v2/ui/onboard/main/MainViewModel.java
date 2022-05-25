package com.example.fitin_v2.ui.onboard.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends AndroidViewModel {
    // onClick 처리 ViewModel을 통해서 Boolean 처리하고 navigation 처리

    private final MutableLiveData<Boolean> _eventSignUp = new MutableLiveData<Boolean>();
    LiveData<Boolean> eventSignUp;

    private final MutableLiveData<Boolean> _eventSignIn = new MutableLiveData<Boolean>();
    LiveData<Boolean> eventSignIn;

    private MutableLiveData<String> _kakao = new MutableLiveData<String>();
    LiveData<String> kakao;
    private MutableLiveData<String> _google = new MutableLiveData<String>();
    LiveData<String> google;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void onEventKakao() {
        _kakao.setValue("KaKao");
    }

    public void onKakaoComplete() {
        _kakao.setValue(null);
    }

    public LiveData<String> getKakao() {
        return kakao = _kakao;
    }

    public void onEventGoogle() {
        _google.setValue("Google");
    }

    public void onGoogleComplete() {
        _google.setValue(null);
    }

    public LiveData<String> getGoogle() {
        return google = _google;
    }



    public LiveData<Boolean> getEventSignUp() {
        return eventSignUp = _eventSignUp;
    }

    public LiveData<Boolean> getEventSignIn() {
        return eventSignIn = _eventSignIn;
    }

    public void onEventSignUp() {
        _eventSignUp.setValue(true);
    }

    public void onEventSignUpComplete() {
        _eventSignUp.setValue(false);
    }

    public void onEventSignIn() {
        _eventSignIn.setValue(true);
    }

    public void onEventSignInComplete() {
        _eventSignIn.setValue(false);
    }
}
