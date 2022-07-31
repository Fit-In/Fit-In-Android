package com.example.fitin_kotlin.ui.onboard.findid

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class FindIdEndViewModel(
    state: SavedStateHandle
) : ViewModel() {

    private val _responseId = MutableLiveData<String>()
    val responseId: LiveData<String>
        get() = _responseId
    init {
        _responseId.value = "당신의 FITIN 아이디는\n" + state.getLiveData<String>("id").value.toString() + "입니다."
//        Log.e("데이터", state.getLiveData<String>("id").value.toString())
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

}