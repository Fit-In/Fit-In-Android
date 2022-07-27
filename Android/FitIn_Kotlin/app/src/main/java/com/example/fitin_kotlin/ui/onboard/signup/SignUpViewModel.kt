package com.example.fitin_kotlin.ui.onboard.signup

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitin_kotlin.data.model.network.request.RequestPhoneNumber
import com.example.fitin_kotlin.data.model.network.request.RequestSignUp
import com.example.fitin_kotlin.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(){
    // 이메일 받아와서 이메일 중복 체크 => API 요청
    val name: MutableLiveData<String> = MutableLiveData<String>()
    val id: MutableLiveData<String> = MutableLiveData<String>()
    val email: MutableLiveData<String> = MutableLiveData<String>()
    val password: MutableLiveData<String> = MutableLiveData<String>()
    val passwordCheck: MutableLiveData<String> = MutableLiveData<String>()

    val phoneNumber: MutableLiveData<String> = MutableLiveData<String>()
    val validationNumber: MutableLiveData<String> = MutableLiveData<String>()
    private lateinit var checkNumber: String

    private val _eventEmailNotDuplicate = MutableLiveData<Boolean>()
    val eventEmailNotDuplicate: LiveData<Boolean>
        get() = _eventEmailNotDuplicate

    fun onEmailDuplicateCheck(view: View) {
        // 이메일 을 받아와서 중복 체크함, 중복이 있으면 true, 없으면 false
        val email: String = id.value + "@" + email.value
        viewModelScope.launch {
            val duplicateCheck = userRepository.getEmailDuplicateCheck(email)
            when (duplicateCheck.isSuccessful) {
                true -> {
                    Log.e("성공", duplicateCheck.body().toString())
                    // false 즉, 이메일이 중복되므로 가입 불가
                    if (duplicateCheck.body().toString() == "false") {
                        _eventEmailNotDuplicate.value = true
                    } else {
                        _eventEmailNotDuplicate.value = false
                    }
                    // 팝업 메시지를 통해서 이메일 중복 됐다고 알려줌
                }
                else -> {
                    _eventEmailNotDuplicate.value = false
                }
            }
        }
    }

    // 인증번호 요청 API => 결과값에 대해서 확인하고 boolean으로 인증 처리 확인
    fun onCoolSms(view: View) {
        val requestPhoneNumber = RequestPhoneNumber(phoneNumber.value)
        viewModelScope.launch {
            val validation = userRepository.postCoolSms(requestPhoneNumber)
            when (validation.isSuccessful) {
                true -> {
                    checkNumber = validation.body().toString()
                    Log.e("성공", "인증번호" + validation.body().toString())
                }
                else -> {
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

    private val _eventPasswordCheck = MutableLiveData<Boolean>()
    val eventPasswordCheck: LiveData<Boolean>
        get() = _eventPasswordCheck

    private fun onPasswordCheck() {
        _eventPasswordCheck.value = password.value.equals(passwordCheck.value)
    }

    // 회원가입 누르면 단 조건 위의 중복 체크 & 인증 처리 다 확인했을 경우 로그인 화면으로 넘어가게 처리 API 요청

    private val _eventSignUp = MutableLiveData<Boolean>()
    val eventSignUp: LiveData<Boolean>
        get() = _eventSignUp

    private val _requestSignUp = MutableLiveData<RequestSignUp>()
    val requestSignUp: LiveData<RequestSignUp?>
        get() = _requestSignUp

//    private val _signUpButtonEnabled = MutableLiveData<Boolean>(false)
//    val signUpButtonEnabled: LiveData<Boolean>
//        get() = _signUpButtonEnabled

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun onSignUp(view: View) {
        // 회원가입 버튼 기본 디폴트 값을 비활성화 상태 + 비밀번호 유효성 검사 추가
        onPasswordCheck()
        if (eventEmailNotDuplicate.value!! && eventNumberCheck.value!! && eventPasswordCheck.value!!) {
            // 회원가입 버튼 활성화
            val email = id.value + "@" + email.value
            // 이메일 & 비밀번호 & 이름 & 전화번호에 대해 정규식 제약조건이 걸려있음, 그에 맞게 입력하게끔 hint로 알려줘야함
//            val requestSignUp = RequestSignUp(email, password.value, name.value, phoneNumber.value?.toLong())
            _requestSignUp.value = RequestSignUp(email, password.value, name.value, phoneNumber.value)
            viewModelScope.launch {
                val signUp = userRepository.postSignUp(_requestSignUp.value!!)
                when (signUp.isSuccessful) {
                    true -> {
                        // 조건문을 통해서 error message가 처음에 존재한다면 해당 부분에 대해서 LiveData<String>으로 설정, Toast message를 띄움
                        // 만약 없다면 그냥 true로 통과하고 데이터 넘기고 로그인 진행
                        if (signUp.body()!!.state == 200) {
                            Log.e("성공", "success")
                            _eventSignUp.value = true
                        } else {
                            val result = signUp.body()!!.error[0].toString()
                            Log.e("결과 문자", result)
                            val failMessage = result.substring(result.lastIndexOf("=")+1,result.indexOf("}"))
                            Log.e("안내 문자", failMessage)
                            _errorMessage.value = failMessage
                        }
                    }
                    // response message 팝업으로 띄우면 됨, 로그로 찍어서 확인하기

                    else -> {
                        _eventSignUp.value = false
                        Log.e("실패", signUp.message())
                    }
                }
            }
        } else {
            _eventSignUp.value = false
        }
    }

    fun onSignUpComplete() {
        _eventSignUp.value = false
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