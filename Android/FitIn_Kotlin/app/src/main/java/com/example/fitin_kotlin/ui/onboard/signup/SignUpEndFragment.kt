package com.example.fitin_kotlin.ui.onboard.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitin_kotlin.R


class SignUpEndFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_end, container, false)
        // MVVM 적용 예정 + 완료 끝난 경우 홈 화면으로 넘어가게 처리함
        // ViewModel 적용 사항 -> 회원가입2에서 Bundle로 데이터 넘겨받음(회원가입2에선 이미 회원가입 API 호출 끝난 상태)
        // 시작하기 버튼 누르면 signIn API 호출해서 토큰 받고 저장하게끔 만듬, 그리고 홈화면으로 넘어감
        // 추후 이용약관과 휴대폰 인증이 추가될 예정
    }

}