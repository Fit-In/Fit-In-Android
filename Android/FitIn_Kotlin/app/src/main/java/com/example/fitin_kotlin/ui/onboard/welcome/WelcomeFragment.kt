package com.example.fitin_kotlin.ui.onboard.welcome

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
import com.example.fitin_kotlin.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint

class WelcomeFragment : Fragment() {
    private val welcomeViewModel: WelcomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentWelcomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        welcomeViewModel.eventSignUp.observe(viewLifecycleOwner, Observer { signUp ->
            if (signUp) {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToSignUpFragment())
                welcomeViewModel.onEventSignUpComplete()
            }
        })

        welcomeViewModel.eventSignIn.observe(viewLifecycleOwner, Observer { signIn ->
            if (signIn) {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToSignInFragment())
                welcomeViewModel.onEventSignInComplete()
            }
        })

        welcomeViewModel.eventKakaoSign.observe(viewLifecycleOwner, Observer { kakao ->
            if (kakao != null) {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToSocialSignFragment(kakao))
                welcomeViewModel.onEventKakaoComplete()
            }
        })

        welcomeViewModel.eventNaverSign.observe(viewLifecycleOwner, Observer { naver ->
            if (naver != null) {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToSocialSignFragment(naver))
                welcomeViewModel.onEventNaverComplete()
            }
        })

        binding.welcomeViewModel = welcomeViewModel


        return binding.root
    }


}