package com.example.fitin_kotlin.ui.onboard.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fitin_kotlin.R
import com.example.fitin_kotlin.databinding.FragmentSignUpSecondBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpSecondFragment : Fragment() {

    private val signupSecondViewModel: SignUpSecondViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSignUpSecondBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_second, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.signUpSecondViewModel = signupSecondViewModel

//        signupSecondViewModel.requestSignUp.observe(viewLifecycleOwner, Observer { signUp ->
//            if (signupSecondViewModel.eventNumberCheck.value == true && signUp != null) {
//                findNavController().navigate(SignUpSecondFragmentDirections.actionSignUpSecondFragmentToSignUpEndFragment(signUp))
//                signupSecondViewModel.onSignUpComplete()
//            }
//        })

        // 위의 코드의 경우 바로 사용자가 입력한 정보를 옵저버 패턴으로 읽어서 현재 2번째 화면을 확인도 안하고 그대로 넘어간 것임
        // 그래서 어떤 값을 옵저빙해서 해당 화면을 사용하게끔 할 것인지 잘 봐야함
        signupSecondViewModel.eventSignUp.observe(viewLifecycleOwner, Observer { succeess ->
            if (succeess) {
                findNavController().navigate(SignUpSecondFragmentDirections.actionSignUpSecondFragmentToSignUpEndFragment(signupSecondViewModel.requestSignUp.value!!))
                signupSecondViewModel.onSignUpComplete()
            }
        })

        signupSecondViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().navigate(SignUpSecondFragmentDirections.actionSignUpSecondFragmentToSignUpFirstFragment())
                signupSecondViewModel.onBackComplete()
            }
        })
        return binding.root
    }

}