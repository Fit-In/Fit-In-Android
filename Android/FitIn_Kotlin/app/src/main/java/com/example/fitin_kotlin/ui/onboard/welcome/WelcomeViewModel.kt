package com.example.fitin_kotlin.ui.onboard.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class WelcomeViewModel : ViewModel() {

    private val _eventSignUp = MutableLiveData<Boolean>()
    val eventSignUp: LiveData<Boolean>
        get() = _eventSignUp

    fun onEventSignUp() {
        _eventSignUp.value = true
    }

    fun onEventSignUpComplete() {
        _eventSignUp.value = false
    }

    private val _eventSignIn = MutableLiveData<Boolean>()
    val eventSignIn: LiveData<Boolean>
        get() = _eventSignIn

    fun onEventSignIn() {
        _eventSignIn.value = true
    }

    fun onEventSignInComplete() {
        _eventSignIn.value = false
    }

    private val _eventKakaoSign = MutableLiveData<String?>()
    val eventKakaoSign: LiveData<String?>
        get() = _eventKakaoSign

    fun onEventKakao() {
        _eventKakaoSign.value = "KaKao"
    }

    fun onEventKakaoComplete() {
        _eventKakaoSign.value = null
    }

    private val _eventNaverSign = MutableLiveData<String?>()
    val eventNaverSign: LiveData<String?>
        get() = _eventNaverSign

    fun onEventNaver() {
        _eventNaverSign.value = "Naver"
    }

    fun onEventNaverComplete() {
        _eventNaverSign.value = null
    }


}