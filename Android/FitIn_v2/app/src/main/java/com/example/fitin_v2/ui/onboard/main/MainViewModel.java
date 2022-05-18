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

    public MainViewModel(@NonNull Application application) {
        super(application);
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
