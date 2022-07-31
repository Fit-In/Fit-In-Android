package com.example.fitin_kotlin.ui.onboard.signin

import android.content.Intent
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
import com.example.fitin_kotlin.databinding.FragmentSignInBinding
import com.example.fitin_kotlin.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSignInBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.signInViewModel = signInViewModel

        signInViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToWelcomeFragment())
                signInViewModel.onBackComplete()
            }
        })

        signInViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        })

        signInViewModel.eventSignIn.observe(viewLifecycleOwner, Observer { signIn ->
            if (signIn) {
                val intentToHome = Intent(activity, HomeActivity::class.java)
                startActivity(intentToHome)
                activity?.overridePendingTransition(0,0)
                activity?.finish()
                signInViewModel.onEventSignInComplete()
            }
        })

        signInViewModel.eventFindId.observe(viewLifecycleOwner, Observer { findId ->
            if (findId) {
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToFindIdFragment())
                signInViewModel.onFindIdComplete()
            }
        })

        return binding.root
    }


}