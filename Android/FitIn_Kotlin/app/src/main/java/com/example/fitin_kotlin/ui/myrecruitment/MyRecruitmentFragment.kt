package com.example.fitin_kotlin.ui.myrecruitment

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
import com.example.fitin_kotlin.databinding.FragmentMyRecruitmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyRecruitmentFragment : Fragment() {

    private val myRecruitmentViewModel: MyRecruitmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMyRecruitmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_recruitment, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.myRecruitmentViewModel = myRecruitmentViewModel

        myRecruitmentViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().popBackStack()
                myRecruitmentViewModel.onBackComplete()
            }
        })

        return binding.root
    }

}