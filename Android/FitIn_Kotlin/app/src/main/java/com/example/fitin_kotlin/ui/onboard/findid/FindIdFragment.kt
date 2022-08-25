package com.example.fitin_kotlin.ui.onboard.findid

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
import com.example.fitin_kotlin.databinding.FragmentFindIdBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindIdFragment : Fragment() {

    private val findIdViewModel: FindIdViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentFindIdBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_id, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.findIdViewModel = findIdViewModel


        findIdViewModel.eventFindId.observe(viewLifecycleOwner, Observer { success ->
            if (success) {
                findNavController().navigate(FindIdFragmentDirections.actionFindIdFragmentToFindIdEndFragment(findIdViewModel.responseId.value!!))
                findIdViewModel.onFindIdComplete()
            }
        })

        findIdViewModel.eventFindPw.observe(viewLifecycleOwner, Observer { findPw ->
            if (findPw) {
                findNavController().navigate(FindIdFragmentDirections.actionFindIdFragmentToFindPwFragment())
                findIdViewModel.onEventFindPwComplete()
            }
        })

        findIdViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().navigate(FindIdFragmentDirections.actionFindIdFragmentToSignInFragment())
                findIdViewModel.onEventBackComplete()
            }
        })

        findIdViewModel.toastMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }


}