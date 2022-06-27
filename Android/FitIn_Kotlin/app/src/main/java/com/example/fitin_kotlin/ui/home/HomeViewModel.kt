package com.example.fitin_kotlin.ui.home

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    prefs: EncryptedSharedPreferenceController
) : ViewModel(){

    val accessToken: MutableLiveData<String> = MutableLiveData<String>()

    init {
        accessToken.value = prefs.getAccessToken()
    }

    fun onGetEmail(view: View) {
        val token = accessToken.value.toString()
        viewModelScope.launch {
            val email = userRepository.getEmail(token)
            when (email.isSuccessful) {
                true -> {
                    Log.e("email", "성공: " + email.body()?.email)
                }
                else -> {
                    Log.e("실패", "error: " + email.message())
                }
            }
        }

    }
}