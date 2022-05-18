package com.example.fitin_v2.ui.onboard.signup;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fitin_v2.model.AccountRequestDto;
import com.example.fitin_v2.repository.UserRepository;

public class SignUpSecondViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    private MutableLiveData<AccountRequestDto> accountRequestDtoMutableLiveData;

    private final MutableLiveData<Boolean> _eventBack = new MutableLiveData<Boolean>();
    LiveData<Boolean> eventBack;

    public SignUpSecondViewModel(AccountRequestDto accountRequestDto, Application application) {
        super(application);
        userRepository = new UserRepository(application);
        if (accountRequestDtoMutableLiveData == null) {
            accountRequestDtoMutableLiveData = new MutableLiveData<>();
            accountRequestDtoMutableLiveData.setValue(accountRequestDto);
        }
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

    public void onRegister(View view) {
        AccountRequestDto accountRequestDto = accountRequestDtoMutableLiveData.getValue();
        userRepository.getAccount(accountRequestDto);
    }







}
