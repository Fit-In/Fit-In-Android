package com.example.fitin_kotlin.ui.recruitment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fitin_kotlin.R
import com.example.fitin_kotlin.databinding.FragmentRecruitmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecruitmentFragment : Fragment() {

    private val recruitmentViewModel: RecruitmentViewModel by viewModels()
    private lateinit var recruitmentAdapter: RecruitmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentRecruitmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recruitment, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.recruitmentViewModel = recruitmentViewModel

        recruitmentAdapter = RecruitmentAdapter(RecruitmentAdapter.OnClickListener {
            recruitmentViewModel.displayRecruitment(it)
        })

        binding.rvRecruitmentList.adapter = recruitmentAdapter

        recruitmentViewModel.recruitmentList.observe(viewLifecycleOwner) {
            it.let {
                recruitmentAdapter.responseRecruitmentList = it
            }
        }

        binding.svSearchRecruitment.isActivated = true
        binding.svSearchRecruitment.onActionViewExpanded()
        binding.svSearchRecruitment.clearFocus()

        binding.svSearchRecruitment.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                (binding.rvRecruitmentList.adapter as RecruitmentAdapter).filter.filter(newText)
                (binding.rvRecruitmentList.adapter as RecruitmentAdapter).notifyDataSetChanged()
                return false
            }

        })

        recruitmentViewModel.requestRecruitment.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                findNavController().navigate(RecruitmentFragmentDirections.actionRecruitmentFragmentToRecruitmentDetailFragment(it))
                recruitmentViewModel.displayRecruitmentFinish()
            }
        })

        recruitmentViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().popBackStack()
                recruitmentViewModel.onBackComplete()
            }
        })

        return binding.root
    }

}