package com.example.fitin_kotlin.ui.onboard.signup

import android.util.Log
import android.view.View
import androidx.lifecycle.*
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

    private val requestSignUp = MutableLiveData<RequestSignUp>()

    init {
        requestSignUp.value = state.getLiveData<RequestSignUp>("RequestSignUp").value
    }

    fun onSignUp(view: View) {
        viewModelScope.launch {
            val signup = userRepository.postSignUp(requestSignUp.value!!)
            Log.e("유저", "user" + requestSignUp!!.value!!.email)
            when (signup.isSuccessful) {
                true -> {
                    Log.e("완료", "응답값" + signup.body()?.email)
                }
                else -> {
                    Log.e("실패", "error: " + signup.message())
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