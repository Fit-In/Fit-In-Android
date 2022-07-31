package com.example.fitin_kotlin.ui.onboard.findpw

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class FindPwEndViewModel : ViewModel(){

    private val _eventSignIn = MutableLiveData<Boolean>()
    val eventSignIn: LiveData<Boolean>
        get() = _eventSignIn

    fun onEventSignIn() {
        _eventSignIn.value = true
    }

    fun onEventSignInComplete() {
        _eventSignIn.value = false
    }
}