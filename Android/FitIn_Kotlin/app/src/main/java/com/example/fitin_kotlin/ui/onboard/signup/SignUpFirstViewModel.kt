package com.example.fitin_kotlin.ui.onboard.signup

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fitin_kotlin.data.model.network.request.RequestSignUp
import dagger.hilt.android.lifecycle.HiltViewModel

class SignUpFirstViewModel : ViewModel(){

    val name: MutableLiveData<String> = MutableLiveData<String>()
    val id: MutableLiveData<String> = MutableLiveData<String>()
    val email: MutableLiveData<String> = MutableLiveData<String>()
    val password: MutableLiveData<String> = MutableLiveData<String>()

    val requestSignUp: MutableLiveData<RequestSignUp?> = MutableLiveData<RequestSignUp?>()

    fun getRequestSignUp(view: View) {
        val email: String = id.value + "@" + email.value
        val signUp = RequestSignUp(email, password.value, name.value)
        requestSignUp.value = signUp
    }

    fun getRequestSignUpComplete() {
        requestSignUp.value = null
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