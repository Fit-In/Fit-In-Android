package com.example.fitin_kotlin.ui.onboard.findpw

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
import com.example.fitin_kotlin.databinding.FragmentFindPwEndBinding
import com.example.fitin_kotlin.ui.onboard.findid.FindIdEndFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

class FindPwEndFragment : Fragment() {

    private val findPwEndViewModel: FindPwEndViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentFindPwEndBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_pw_end, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.findPwEndViewModel = findPwEndViewModel

        findPwEndViewModel.eventSignIn.observe(viewLifecycleOwner, Observer { login ->
            if (login) {
                findNavController().navigate(FindPwEndFragmentDirections.actionFindPwEndFragmentToSignInFragment())
                findPwEndViewModel.onEventSignInComplete()
            }
        })

        return binding.root
    }

}