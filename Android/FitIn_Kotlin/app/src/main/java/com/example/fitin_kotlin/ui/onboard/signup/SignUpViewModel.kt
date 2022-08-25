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
    private var checkNumber: String? = null

    private val _eventEmailNotDuplicate = MutableLiveData<Boolean>()
    val eventEmailNotDuplicate: LiveData<Boolean>
        get() = _eventEmailNotDuplicate

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    fun onEmailDuplicateCheck(view: View) {
        // 이메일 을 받아와서 중복 체크함, 중복이 있으면 true, 없으면 false
        val email: String = id.value + "@" + email.value
        viewModelScope.launch {
            val duplicateCheck = userRepository.getEmailDuplicateCheck(email)
            when (duplicateCheck.isSuccessful) {
                true -> {
                    Log.e("성공", duplicateCheck.body().toString())
                    // false 즉, 이메일이 중복되므로 가입 불가
                    if (duplicateCheck.body().toString().contains("false")) {
                        _eventEmailNotDuplicate.value = true
                        _toastMessage.value = "해당 이메일로 가입이 가능합니다."
                    } else {
                        _eventEmailNotDuplicate.value = false
                        _toastMessage.value = "해당 이메일로 가입할 수 없습니다. 다시 중복 체크를 해주세요."
                    }
                }
                else -> {
                    _eventEmailNotDuplicate.value = false
                    _toastMessage.value = "네트워크 통신이 원활하지 않습니다."
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
                    // 번호 안 써도 인증번호 날아감, 이 부분 response로 받게 수정
                    checkNumber = validation.body().toString()
                    _toastMessage.value = "인증번호가 전송되었습니다."
                    Log.e("성공", "인증번호" + validation.body().toString())
                }
                else -> {
                    _toastMessage.value = "휴대폰 번호를 다시 입력해주세요."
                    Log.e("실패", "fail " + validation.message())
                }
            }
        }
    }

    private val _eventNumberCheck = MutableLiveData<Boolean>()
    val eventNumberCheck: LiveData<Boolean>
        get() = _eventNumberCheck

    fun onValidationCheck(view: View) {
        if (checkNumber == null || checkNumber != validationNumber.value) {
            _eventNumberCheck.value = false
            _toastMessage.value = "인증번호를 다시 입력해, 번호확인을 다시 눌러주세요."
        } else {
            _eventNumberCheck.value = checkNumber == validationNumber.value
            _toastMessage.value = "인증확인 되었습니다."
        }
        // 예외처리 유저가 인증번호 확인을 안 누르고 바로 회원가입을 누르면 튕기기 때문에 초기화 필요
        // boolean 값에 따라서 팝업 메시지 띄워줌 인증 성공 & 실패 구분
    }

    private val _eventPasswordCheck = MutableLiveData<Boolean>()
    val eventPasswordCheck: LiveData<Boolean>
        get() = _eventPasswordCheck

    private fun onPasswordCheck() {
        if (password.value!!.equals(passwordCheck.value)) {
            _eventPasswordCheck.value = true

        } else {
            _eventPasswordCheck.value = false
            _toastMessage.value = "비밀번호가 일치하지 않습니다."
        }
    }

    // 회원가입 누르면 단 조건 위의 중복 체크 & 인증 처리 다 확인했을 경우 로그인 화면으로 넘어가게 처리 API 요청

    private val _eventSignUp = MutableLiveData<Boolean>()
    val eventSignUp: LiveData<Boolean>
        get() = _eventSignUp

    private val _requestSignUp = MutableLiveData<RequestSignUp>()
    val requestSignUp: LiveData<RequestSignUp?>
        get() = _requestSignUp


    fun onSignUp(view: View) {
        // 회원가입 버튼 기본 디폴트 값을 비활성화 상태 + 비밀번호 유효성 검사 추가
        onPasswordCheck()
        try {
            if (eventEmailNotDuplicate.value!! && eventNumberCheck.value!! && eventPasswordCheck.value!!) {
                // 회원가입 버튼 활성화
                val email = id.value + "@" + email.value
                // 이메일 & 비밀번호 & 이름 & 전화번호에 대해 정규식 제약조건이 걸려있음, 그에 맞게 입력하게끔 hint로 알려줘야함
                _requestSignUp.value =
                    RequestSignUp(email, password.value, name.value, phoneNumber.value)
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
                                val failMessage = result.substring(
                                    result.lastIndexOf("=") + 1,
                                    result.indexOf("}")
                                )
                                Log.e("안내 문자", failMessage)
                                _toastMessage.value = failMessage
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
                _toastMessage.value = "네트워크 연결을 확인해주세요"
            }
        } catch (e: Exception) {
            _toastMessage.value = "입력하지 않은 값이 있습니다."
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