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
            val signin = userRepository.postSignIn(requestSignIn)
            when (signin.isSuccessful) {
                true -> {
                    prefs.setAccessToken(signin.body()!!.accessToken)
                    prefs.setRefreshToken(signin.body()!!.refreshToken)
                    // 뉴스 호출 생략
                }
                else -> {
                    Log.e("실패", "error " + signin.message())
                }
            }
        }
        _eventSignIn.value = true
    }


    fun onEventSignInComplete() {
        _eventSignIn.value = false
    }

}