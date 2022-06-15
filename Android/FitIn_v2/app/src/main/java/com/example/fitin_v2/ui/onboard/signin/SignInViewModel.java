package com.example.fitin_v2.ui.onboard.signin;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fitin_v2.model.AccountLoginDto;
import com.example.fitin_v2.repository.NewsRepository;
import com.example.fitin_v2.repository.UserRepository;

import io.reactivex.disposables.CompositeDisposable;

public class SignInViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private NewsRepository newsRepository;
    private final CompositeDisposable disposable = new CompositeDisposable();

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
        newsRepository = new NewsRepository(application);
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
        newsRepository.callNews();
        // 로그인 검증 설정하기 결과값 바탕으로 가져온 값에 따라서 홈으로 넘어갈지 말지 처리 추가하기
//        String validation = Preferences.getLogin("오류");
//        if(validation.equals("Success")) {
////            Log.e("token", "tokens " + token);
//            _eventSignIn.setValue(true);
//        } else if(validation.equals("Fail")) {
////            Log.e("token", "tokens" + token);
//            _eventSignIn.setValue(false);
//        } else {
//            _eventSignIn.setValue(false);
//        }
        // 뉴스 callAPI 데이터 불러와서 DB에 저장

        _eventSignIn.setValue(true);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        disposable.clear();
    }
}
