package com.example.fitin_kotlin.ui.onboard.signin

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.data.model.network.request.RequestSignIn
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

    fun onSignIn(view: View) {
        val request = RequestSignIn(email.value, password.value)
        viewModelScope.launch {
            val signin = userRepository.postSignIn(request)
            when (signin.isSuccessful) {
                true -> {
                    Log.e("token", "성공: " + signin.body()?.accessToken)
                    prefs.setAccessToken(signin.body()!!.accessToken)
                    prefs.setRefreshToken(signin.body()!!.refreshToken)
                }
                else -> {
                    Log.e("실패", "error " + signin.message())
                }
            }
        }
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

}