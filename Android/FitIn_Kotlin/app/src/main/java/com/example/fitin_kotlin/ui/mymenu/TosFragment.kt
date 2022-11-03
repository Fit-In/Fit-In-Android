package com.example.fitin_kotlin.ui.mymenu

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
import com.example.fitin_kotlin.databinding.FragmentTosBinding
import dagger.hilt.android.AndroidEntryPoint

class TosFragment : Fragment() {

    private val tosViewModel: TosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentTosBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tos, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.tosViewModel = tosViewModel

        tosViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().popBackStack()
                tosViewModel.onBackComplete()
            }
        })

        return binding.root
    }

}