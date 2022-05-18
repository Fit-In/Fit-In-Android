package com.example.fitin_v2.ui.onboard.signin;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fitin_v2.model.AccountLoginDto;
import com.example.fitin_v2.model.TokenDto;
import com.example.fitin_v2.repository.UserRepository;
import com.example.fitin_v2.ui.HomeActivity;
import com.example.fitin_v2.util.Preferences;

import timber.log.Timber;

public class SignInViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<AccountLoginDto> userMutableLiveData;

    private final MutableLiveData<Boolean> _eventSignIn = new MutableLiveData<Boolean>();
    LiveData<Boolean> eventSignIn;

    private final MutableLiveData<Boolean> _eventBack = new MutableLiveData<Boolean>();
    LiveData<Boolean> eventBack;

    public SignInViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);

    }

    public MutableLiveData<AccountLoginDto> getUser() {

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public LiveData<Boolean> getBack() {
        return eventBack = _eventBack;
    }

    public void onBack() {
        _eventBack.setValue(true);
    }

    public void onBackComplete() {
        _eventBack.setValue(false);
    }

    public LiveData<Boolean> getEventSignIn() {
        return eventSignIn = _eventSignIn;
    }

    public void onEventSignInComplete() {
        _eventSignIn.setValue(false);
    }

    public void onLogin(View view) {
        AccountLoginDto accountLoginDto = new AccountLoginDto(email.getValue(), password.getValue());
        userRepository.getToken(accountLoginDto);
        _eventSignIn.setValue(true);
        Log.e("Token", Preferences.getAccessToken("nein"));
    }
}
