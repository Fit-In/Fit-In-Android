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

        signupSecondViewModel.requestSignUp.observe(viewLifecycleOwner, Observer { signUp ->
            if (signUp != null) {
                findNavController().navigate(SignUpSecondFragmentDirections.actionSignUpSecondFragmentToSignUpEndFragment(signUp))
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