package com.example.fitin_kotlin.ui.news

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
import com.example.fitin_kotlin.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.homeViewModel = newsViewModel

        binding.rvNewsList.adapter = NewsAdapter(NewsAdapter.OnClickListener {
            newsViewModel.displayNews(it)
        })

        newsViewModel.requestNews.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                findNavController().navigate(
                    NewsFragmentDirections.actionHomeFragmentToDetailFragment(it))
                newsViewModel.displayNewsFinish()
            }
        })

        return binding.root
    }

}