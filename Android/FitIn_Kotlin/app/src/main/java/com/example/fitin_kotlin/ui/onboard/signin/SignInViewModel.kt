package com.example.fitin_kotlin.ui.onboard.signin

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.data.model.network.request.RequestSignIn
import com.example.fitin_kotlin.data.repository.NewsRepository
import com.example.fitin_kotlin.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val prefs: EncryptedSharedPreferenceController
) : ViewModel(){

    val email: MutableLiveData<String> = MutableLiveData<String>()
    val password: MutableLiveData<String> = MutableLiveData<String>()

    private val _eventSignIn = MutableLiveData<Boolean>()
    val eventSignIn: LiveData<Boolean>
        get() = _eventSignIn

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun onSignIn(view: View) {
        val requestSignIn = RequestSignIn(email.value, password.value)
        viewModelScope.launch {
            val signIn = userRepository.postSignIn(requestSignIn)
            when (signIn.isSuccessful) {
                true -> {
                    if (signIn.body()!!.state == 200) {
//                        Log.e("성공", signIn.body()!!.data.toString())
                        val result = signIn.body()!!.data.toString()
                        val accessToken = result.substring(result.indexOf("accessToken=") + 12, result.indexOf(", refreshToken"))
                        val refreshToken = result.substring(result.indexOf("refreshToken=") + 13, result.indexOf(", refreshTokenExpiresIn"))
                        prefs.setAccessToken(accessToken)
                        prefs.setRefreshToken(refreshToken)
//                        Log.e("Access Token", prefs.getAccessToken()!!)
//                        Log.e("Refresh Token", prefs.getRefreshToken()!!)
                        _eventSignIn.value = true
                    } else {
                        if (signIn.body()!!.message.isNotEmpty()) {
//                            Log.e("실패", signIn.body()!!.message)
                            val userNotExist = signIn.body()!!.message
                            _errorMessage.value = userNotExist
                        } else {
//                            Log.e("실패", signIn.body()!!.error[0].toString())
                            val result = signIn.body()!!.error[0].toString()
                            val failMessage = result.substring(result.lastIndexOf("=")+1,result.indexOf("}"))
//                            Log.e("안내 문자", failMessage)
                            _errorMessage.value = failMessage
                        }
                    }
                }
                else -> {
//                    Log.e("실패", "error " + signIn.message())
                    if (signIn.code() == 401) {
                        _errorMessage.value = "비밀번호가 일치하지 않습니다."
                    }
                    _eventSignIn.value = false
                }
            }
        }

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

    private val _eventBack = MutableLiveData<Boolean>()
    val eventBack: LiveData<Boolean>
        get() = _eventBack

    fun onBack() {
        _eventBack.value = true
    }

    fun onBackComplete() {
        _eventBack.value = false
    }

    private val _eventFindId = MutableLiveData<Boolean>()
    val eventFindId: LiveData<Boolean>
        get() = _eventFindId

    fun onFindId() {
        _eventFindId.value = true
    }

    fun onFindIdComplete() {
        _eventFindId.value = false
    }

}