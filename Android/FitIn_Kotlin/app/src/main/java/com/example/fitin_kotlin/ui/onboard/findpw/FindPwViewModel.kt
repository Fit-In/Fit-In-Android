package com.example.fitin_kotlin.ui.onboard.findpw

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitin_kotlin.data.model.network.request.RequestFindPassword
import com.example.fitin_kotlin.data.model.network.request.RequestPhoneNumber
import com.example.fitin_kotlin.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindPwViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(){

    val email: MutableLiveData<String> = MutableLiveData<String>()
    val name: MutableLiveData<String> = MutableLiveData<String>()
    val phoneNumber: MutableLiveData<String> = MutableLiveData<String>()
    val validationNumber: MutableLiveData<String> = MutableLiveData<String>()
    private lateinit var checkNumber: String

    // 인증번호 요청 API => 결과값에 대해서 확인하고 boolean으로 인증 처리 확인
    fun onCoolSms(view: View) {
        val requestPhoneNumber = RequestPhoneNumber(phoneNumber.value)
        viewModelScope.launch {
            val validation = userRepository.postCoolSms(requestPhoneNumber)
            when (validation.isSuccessful) {
                true -> {
                    // 번호 안 써도 인증번호 날아감, 이 부분 response로 받게 수정
                    checkNumber = validation.body().toString()
                    Log.e("성공", "인증번호" + validation.body().toString())
                }
                else -> {
                    _errorMessage.value = "휴대폰 번호를 다시 입력해주세요"
                    Log.e("실패", "fail " + validation.message())
                }
            }
        }
    }

    private val _eventNumberCheck = MutableLiveData<Boolean>()
    val eventNumberCheck: LiveData<Boolean>
        get() = _eventNumberCheck

    fun onValidationCheck(view: View) {
        _eventNumberCheck.value = checkNumber == validationNumber.value
        // 예외처리 유저가 인증번호 확인을 안 누르고 바로 회원가입을 누르면 튕기기 때문에 초기화 필요
        // boolean 값에 따라서 팝업 메시지 띄워줌 인증 성공 & 실패 구분
    }


    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _eventFindPw = MutableLiveData<Boolean>()
    val eventFindPw: LiveData<Boolean>
        get() = _eventFindPw

    fun onFindPassword(view: View) {
        try {
            if (eventNumberCheck.value!!) {
                val requestFindPw = RequestFindPassword(email.value!!, name.value!!, phoneNumber.value!!)
                viewModelScope.launch {
                    val findPw = userRepository.postFindPassword(requestFindPw)
                    when (findPw.isSuccessful) {
                        true -> {
                            if (findPw.body()!!.state == 200) {
                                Log.e("성공", findPw.body()!!.data.toString())
                                _eventFindPw.value = true
                            } else {
                                Log.e("실패", findPw.body()!!.message)
                                _errorMessage.value = findPw.body()!!.message
                            }
                        }
                        else -> {
                            Log.e("실패", "서버 연결 실패")
                            _errorMessage.value = "값을 다시 입력해주세요"
                        }
                    }
                }
            } else {
                Log.e("인증 실패", "인증 처리 오류")
                _eventFindPw.value = false
            }
        } catch (e: Exception) {
            _errorMessage.value = "입력하지 않은 값이 있습니다."
        }
    }

    fun onFindPasswordComplete() {
        _eventFindPw.value = false
    }

    private val _eventFindId = MutableLiveData<Boolean>()
    val eventFindId: LiveData<Boolean>
        get() = _eventFindId

    fun onEventFindId() {
        _eventFindId.value = true
    }

    fun onEventFindIdComplete() {
        _eventFindId.value = false
    }

    private val _eventBack = MutableLiveData<Boolean>()
    val eventBack: LiveData<Boolean>
        get() = _eventBack

    fun onEventBack() {
        _eventBack.value = true
    }

    fun onEventBackComplete() {
        _eventBack.value = false
    }
}