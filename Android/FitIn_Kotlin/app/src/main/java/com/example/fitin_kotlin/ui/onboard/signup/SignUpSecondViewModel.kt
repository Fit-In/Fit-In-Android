package com.example.fitin_kotlin.ui.onboard.signup

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.fitin_kotlin.data.model.network.request.RequestPhoneNumber
import com.example.fitin_kotlin.data.model.network.request.RequestSignUp
import com.example.fitin_kotlin.data.repository.UserRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpSecondViewModel @Inject constructor(
    private val userRepository: UserRepository,
    state: SavedStateHandle
) : ViewModel(){

    val phoneNumber: MutableLiveData<String> = MutableLiveData<String>()
    val validationNumber: MutableLiveData<String> = MutableLiveData<String>()
    private lateinit var checkNumber: String

    private val _requestSignUp = MutableLiveData<RequestSignUp>()
    val requestSignUp: LiveData<RequestSignUp?>
        get() = _requestSignUp

    init {
        _requestSignUp.value = state.getLiveData<RequestSignUp>("RequestSignUp").value
    }

    fun onCoolSms(view: View) {
        val requestPhoneNumber = RequestPhoneNumber(phoneNumber.value)
        viewModelScope.launch {
            val validation = userRepository.postCoolSms(requestPhoneNumber)
            when (validation.isSuccessful) {
                true -> {
                    checkNumber = validation.body().toString()
                }
                else -> {
                    Log.e("실패", "error " + validation.message())
                }
            }
        }
    }

    private val _eventNumberCheck = MutableLiveData<Boolean>()
    val eventNumberCheck: LiveData<Boolean>
        get() = _eventNumberCheck

    fun onValidationCheck(view: View) {
        // 아래 로직 되지 않으면 조건문으로
        _eventNumberCheck.value = checkNumber == validationNumber.value
    }

    private val _eventSignUp = MutableLiveData<Boolean>()
    val eventSignUp: LiveData<Boolean>
        get() = _eventSignUp

    fun onSignUp(view: View) {
        if (eventNumberCheck.value!!) {
            viewModelScope.launch {
                val signup = userRepository.postSignUp(_requestSignUp.value!!)
                Log.e("유저", "user" + _requestSignUp!!.value!!.email)
                when (signup.isSuccessful) {
                    true -> {
//                        Log.e("완료", "응답값" + signup.body()?.email)
                    }
                    else -> {
                        Log.e("실패", "error: " + signup.message())
                    }
                }
                _eventSignUp.value = true
            }
        } else {
            Log.e("fail", "휴대폰 인증 실패")
        }
    }

    fun onEventSignUpComplete() {
        _eventSignUp.value = false
    }

    fun onSignUpComplete() {
        _requestSignUp.value = null
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