package com.example.fitin_kotlin.ui.onboard.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.fitin_kotlin.R
import com.example.fitin_kotlin.databinding.FragmentSignUpEndBinding
import com.example.fitin_kotlin.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpEndFragment : Fragment() {

    private val signUpEndViewModel: SignUpEndViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSignUpEndBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up_end, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.signUpEndViewModel = signUpEndViewModel

        signUpEndViewModel.eventSignIn.observe(viewLifecycleOwner, Observer { signIn ->
            if (signIn) {
                val intentToHome = Intent(activity, HomeActivity::class.java)
                startActivity(intentToHome)
                activity?.overridePendingTransition(0,0)
                activity?.finish()
                signUpEndViewModel.onEventSignInComplete()
            }
        })

        return binding.root
    }

}