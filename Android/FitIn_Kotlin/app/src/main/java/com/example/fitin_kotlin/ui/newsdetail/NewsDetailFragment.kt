package com.example.fitin_kotlin.ui.newsdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.fitin_kotlin.R
import com.example.fitin_kotlin.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {

    private val newsDetailViewModel: NewsDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewsDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_detail, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.detailViewModel = newsDetailViewModel

        return binding.root
    }


}