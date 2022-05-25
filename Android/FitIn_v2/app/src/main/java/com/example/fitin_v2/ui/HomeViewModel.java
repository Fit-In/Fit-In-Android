package com.example.fitin_v2.ui;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fitin_v2.model.AccountResponseDto;
import com.example.fitin_v2.repository.UserRepository;
import com.example.fitin_v2.util.Preferences;

import io.reactivex.disposables.CompositeDisposable;

public class HomeViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private final CompositeDisposable disposable = new CompositeDisposable();

    public MutableLiveData<String> _email = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _eventLogout = new MutableLiveData<>();
    LiveData<Boolean> eventLogout;

    public HomeViewModel(@NonNull Application application) {
        super(application);

        userRepository = new UserRepository(application);
    }

    public void reIssue(View view) {
        userRepository.getReissue();
        Log.e("At", Preferences.getAccessToken("nein"));
        Log.e("Rt", Preferences.getRefreshToken("nein"));

    }

    public void getEmail(View view) {
        _email.setValue(userRepository.getEmail());
    }



    public void getLogout(View view) {
        userRepository.getLogouts();
        Preferences.clearToken();
        Log.e("At", Preferences.getAccessToken("nein"));
        _eventLogout.setValue(true);
    }

    public LiveData<Boolean> onLogout() {
        return eventLogout = _eventLogout;
    }

    public void onClear(View view) {
        _email.setValue(null);

    }

    @Override
    protected void onCleared() {
        super.onCleared();

        disposable.clear();
    }
}
