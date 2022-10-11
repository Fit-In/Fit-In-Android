package com.example.fitin_kotlin.ui.onboard.signup

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.data.model.network.request.RequestSignIn
import com.example.fitin_kotlin.data.model.network.request.RequestSignUp
import com.example.fitin_kotlin.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpEndViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val prefs: EncryptedSharedPreferenceController,
    state: SavedStateHandle
) : ViewModel() {

    private val requestSignIn = MutableLiveData<RequestSignUp>()

    init {
        requestSignIn.value = state.getLiveData<RequestSignUp>("RequestSignUp").value
    }

    private val _eventSignIn = MutableLiveData<Boolean>()
    val eventSignIn: LiveData<Boolean>
        get() = _eventSignIn

    fun onSignIn(view: View) {
        val requestSignIn = RequestSignIn(requestSignIn.value?.email, requestSignIn.value?.password)
        viewModelScope.launch {
            val signIn = userRepository.postSignIn(requestSignIn)
            when (signIn.isSuccessful) {
                true -> {
                    if (signIn.body()!!.state == 200) {
                        Log.e("성공", signIn.body()!!.data.toString())
                        val result = signIn.body()!!.data.toString()
                        val accessToken = result.substring(result.indexOf("accessToken=") + 12, result.indexOf(", refreshToken"))
                        val refreshToken = result.substring(result.indexOf("refreshToken=") + 13, result.indexOf(", refreshTokenExpiresIn"))
                        prefs.setAccessToken(accessToken)
                        prefs.setRefreshToken(refreshToken)
                        prefs.setUserEmail(requestSignIn.email.toString())
                        Log.e("Access Token", prefs.getAccessToken()!!)
                        Log.e("Refresh Token", prefs.getRefreshToken()!!)
                        _eventSignIn.value = true
                    }
                    // 예외 처리는 추후 생각, 애초에 예외가 생길 수 있는 FlowChart가 아님
                    // 뉴스 호출 생략
                }
                else -> {
                    Log.e("실패", "error " + signIn.message())
                    _eventSignIn.value = false
                }
            }
        }
    }


    fun onEventSignInComplete() {
        _eventSignIn.value = false
    }

}