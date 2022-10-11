package com.example.fitin_kotlin.ui.recruitmentdetail

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
import com.example.fitin_kotlin.databinding.FragmentRecruitmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecruitmentDetailFragment : Fragment() {

    private val recruitmentDetailViewModel: RecruitmentDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentRecruitmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recruitment_detail, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.detailViewModel = recruitmentDetailViewModel

        recruitmentDetailViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().navigate(RecruitmentDetailFragmentDirections.actionRecruitmentDetailFragmentToRecruitmentFragment())
                recruitmentDetailViewModel.onBackComplete()
            }
        })

        recruitmentDetailViewModel.saveId.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                findNavController().navigate(RecruitmentDetailFragmentDirections.actionRecruitmentDetailFragmentToAddBookmarkFragment(it))
                recruitmentDetailViewModel.onAddBookmarkComplete()
            }
        })

        return binding.root
    }

}