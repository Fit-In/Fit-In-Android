package com.example.fitin_v2.ui.onboard.signup;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fitin_v2.model.AccountRequestDto;
import com.example.fitin_v2.model.AccountResponseDto;
import com.example.fitin_v2.repository.UserRepository;

public class SignUpFirstViewModel extends AndroidViewModel {

    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> emailId = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<AccountRequestDto> accountDto;

    private final MutableLiveData<Boolean> _eventBack = new MutableLiveData<Boolean>();
    LiveData<Boolean> eventBack;


    public SignUpFirstViewModel(@NonNull Application application) {
        super(application);

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

    public MutableLiveData<AccountRequestDto> getAccount() {
        if (accountDto == null) {
            accountDto = new MutableLiveData<>();
        }
        return accountDto;
    }

    public void getUser(View view) {
        String emails = emailId.getValue() + "@" + email.getValue();
        AccountRequestDto accountRequestDto = new AccountRequestDto(emails,password.getValue(),name.getValue());
        accountDto.setValue(accountRequestDto);
    }



    public LiveData<AccountRequestDto> getEventSignUpComplete() {
        return accountDto = null;
    }


}
