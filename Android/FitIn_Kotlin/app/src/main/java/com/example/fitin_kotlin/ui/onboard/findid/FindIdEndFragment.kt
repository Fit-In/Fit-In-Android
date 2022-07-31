package com.example.fitin_kotlin.ui.onboard.findid

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
import com.example.fitin_kotlin.databinding.FragmentFindIdEndBinding
import dagger.hilt.android.AndroidEntryPoint

class FindIdEndFragment : Fragment() {

    private val findIdEndViewModel: FindIdEndViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentFindIdEndBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_id_end, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.findIdEndViewModel = findIdEndViewModel

        findIdEndViewModel.eventSignIn.observe(viewLifecycleOwner, Observer { login ->
            if (login) {
                findNavController().navigate(FindIdEndFragmentDirections.actionFindIdEndFragmentToSignInFragment())
                findIdEndViewModel.onEventSignInComplete()
            }
        })

        return binding.root
    }

}