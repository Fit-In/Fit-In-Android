package com.example.fitin_kotlin.ui.onboard.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitin_kotlin.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    /*
    init을 통해서 callAPI 호출, 그러기 위해서 Hilt 사용해서 DI를 통해서 Repository 주입필요
     */

    init {
        viewModelScope.launch {
            newsRepository.callNews()
        }
    }

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