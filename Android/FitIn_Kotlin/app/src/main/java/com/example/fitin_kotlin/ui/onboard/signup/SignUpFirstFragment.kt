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
import com.example.fitin_kotlin.databinding.FragmentSignUpFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFirstFragment : Fragment() {

    private val signUpFirstViewModel: SignUpFirstViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentSignUpFirstBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_first, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        signUpFirstViewModel.requestSignUp.observe(viewLifecycleOwner, Observer { signUp ->
            if (signUp != null) {
                findNavController().navigate(SignUpFirstFragmentDirections.actionSignUpFirstFragmentToSignUpSecondFragment(signUp))
                signUpFirstViewModel.getRequestSignUpComplete()
            }
        })

        signUpFirstViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().navigate(SignUpFirstFragmentDirections.actionSignUpFirstFragmentToWelcomeFragment())
                signUpFirstViewModel.onBackComplete()
            }

        })

        binding.signUpFirstViewModel = signUpFirstViewModel

        return binding.root
    }

}