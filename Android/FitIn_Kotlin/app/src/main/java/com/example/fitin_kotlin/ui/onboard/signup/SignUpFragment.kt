package com.example.fitin_kotlin.ui.onboard.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fitin_kotlin.R
import com.example.fitin_kotlin.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSignUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.signUpViewModel = signUpViewModel

        signUpViewModel.eventEmailNotDuplicate.observe(viewLifecycleOwner, Observer { duplicateCheck ->
            if (duplicateCheck) {
                Toast.makeText(requireContext(), "사용 가능한 이메일입니다", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "중복된 이메일이 존재합니다.", Toast.LENGTH_SHORT).show()
            }
        })

        signUpViewModel.eventNumberCheck.observe(viewLifecycleOwner, Observer { numberCheck ->
            if (numberCheck) {
                Toast.makeText(requireContext(), "인증에 성공했습니다", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "인증에 실패했습니다", Toast.LENGTH_SHORT).show()
            }
        })

        signUpViewModel.eventPasswordCheck.observe(viewLifecycleOwner, Observer { passwordCheck ->
            if (!passwordCheck) {
                Toast.makeText(requireContext(), "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            }
        })

        signUpViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        })

        signUpViewModel.eventSignUp.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignUpEndFragment(signUpViewModel.requestSignUp.value!!))
                signUpViewModel.onSignUpComplete()
            }
        })

        signUpViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToWelcomeFragment())
                signUpViewModel.onBackComplete()
            }
        })

        return binding.root
    }

}