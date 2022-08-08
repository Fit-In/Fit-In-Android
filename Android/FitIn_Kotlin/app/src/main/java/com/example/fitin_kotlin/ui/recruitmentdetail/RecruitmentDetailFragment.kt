package com.example.fitin_kotlin.ui.recruitmentdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.fitin_kotlin.R
import com.example.fitin_kotlin.databinding.FragmentRecruitmentDetailBinding

class RecruitmentDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentRecruitmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recruitment_detail, container, false)
        return binding.root
    }

}