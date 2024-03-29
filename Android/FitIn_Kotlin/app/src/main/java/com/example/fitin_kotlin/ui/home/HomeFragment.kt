package com.example.fitin_kotlin.ui.home

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
import com.example.fitin_kotlin.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeViewModel = homeViewModel

        binding.rvNewsList.adapter = HomeNewsAdapter(HomeNewsAdapter.OnClickListener {
            homeViewModel.onEventNewsDetail(it)
        })

        binding.rvRecruitmentList.adapter = HomeRecruitmentAdapter(HomeRecruitmentAdapter.OnClickListener {
            homeViewModel.onEventRecruitmentDetail(it)
        })

        homeViewModel.eventNewsList.observe(viewLifecycleOwner, Observer { list ->
            if (list) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewsFragment())
                homeViewModel.onNewsListFinish()
            }
        })




        homeViewModel.requestNews.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewsDetailFragment(it))
                homeViewModel.onEventNewsDetailFinish()
            }
        })

        homeViewModel.eventRecruitmentList.observe(viewLifecycleOwner, Observer { list ->
            if (list) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRecruitmentFragment())
                homeViewModel.onRecruitmentListFinish()
            }
        })



        homeViewModel.requestRecruitment.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRecruitmentDetailFragment(it))
                homeViewModel.onEventRecruitmentDetailFinish()
            }
        })

        return binding.root
    }

}