package com.example.fitin_kotlin.ui.mymenu

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.data.model.network.request.RequestSignOut
import com.example.fitin_kotlin.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyMenuViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val prefs: EncryptedSharedPreferenceController
) : ViewModel() {
    // TODO 로그아웃 버튼 누르면 Activity 종료해서 Welcome로 나가게 처리하기 + SharedPreferences 값 다 삭제하기 + 로그아웃 API로 진행하기
    // TODO 로그아웃 API의 경우 state 200일 경우 LiveData Boolean true 처리 + Fragment에서 Activity finish 처리를 함
    // TODO 이용약관 Navigation 처리만 진행하기 이용약관 내용은 단순한 TextView + ScrollView
    private val _eventTos = MutableLiveData<Boolean>()
    val eventTos: LiveData<Boolean>
        get() = _eventTos

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    fun onEventTos() {
        _eventTos.value = true
    }

    fun onEventTosComplete() {
        _eventTos.value = false
    }

    private val _eventSignOut = MutableLiveData<Boolean>()
    val eventSignOut: LiveData<Boolean>
        get() = _eventSignOut

    fun onSignOut(view: View) {
        val requestSignOut =
            RequestSignOut(prefs.getAccessToken().toString(), prefs.getRefreshToken().toString())
        viewModelScope.launch {
            val signOut = userRepository.postSignOut(requestSignOut)
            when (signOut.isSuccessful) {
                true ->
                    if (signOut.body()!!.state == 200) {
                        prefs.deleteToken()
                        _eventSignOut.value = true
                    } else {
                        Log.e("실패", signOut.body()!!.message)
                    }
                else -> {
                    Log.e("실패", signOut.body()!!.message)

                }
            }
        }
    }

    fun onSignOutComplete() {
        _eventSignOut.value = false
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
