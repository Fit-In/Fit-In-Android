package com.example.fitin_kotlin.ui.mynews

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
import com.example.fitin_kotlin.databinding.FragmentMyNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyNewsFragment : Fragment() {

    private val myNewsViewModel: MyNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentMyNewsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_news, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.myNewsViewModel = myNewsViewModel

        myNewsViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().popBackStack()
                myNewsViewModel.onBackComplete()
            }
        })

        return binding.root
    }

}