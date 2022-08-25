package com.example.fitin_kotlin.ui.onboard.findpw

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
import com.example.fitin_kotlin.databinding.FragmentFindPwBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindPwFragment : Fragment() {

    private val findPwViewModel: FindPwViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentFindPwBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_pw, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.findPwViewModel = findPwViewModel


        findPwViewModel.eventFindId.observe(viewLifecycleOwner, Observer { findId ->
            if (findId) {
                findNavController().navigate(FindPwFragmentDirections.actionFindPwFragmentToFindIdFragment())
                findPwViewModel.onEventFindIdComplete()
            }
        })

        findPwViewModel.eventFindPw.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                findNavController().navigate(FindPwFragmentDirections.actionFindPwFragmentToFindPwEndFragment())
                findPwViewModel.onFindPasswordComplete()
            }
        })

        findPwViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().navigate(FindPwFragmentDirections.actionFindPwFragmentToSignInFragment())
                findPwViewModel.onEventBackComplete()
            }
        })

        findPwViewModel.toastMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        })


        return binding.root
    }

}