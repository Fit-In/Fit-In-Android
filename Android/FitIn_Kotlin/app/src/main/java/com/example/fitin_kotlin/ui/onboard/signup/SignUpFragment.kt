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


        signUpViewModel.toastMessage.observe(viewLifecycleOwner, Observer { toastMessage ->
            Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
        })

        signUpViewModel.eventSignUp.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignUpEndFragment(signUpViewModel.requestSignUp.value!!))
                signUpViewModel.onSignUpComplete()
            }
        })

        signUpViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().popBackStack()
                signUpViewModel.onBackComplete()
            }
        })

        return binding.root
    }

}