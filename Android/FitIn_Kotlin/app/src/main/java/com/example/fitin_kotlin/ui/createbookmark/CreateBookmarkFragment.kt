package com.example.fitin_kotlin.ui.createbookmark

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
import com.example.fitin_kotlin.databinding.FragmentCreateBookmarkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateBookmarkFragment : Fragment() {

    private val createBookmarkViewModel: CreateBookmarkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCreateBookmarkBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_bookmark, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.createBookmarkViewModel = createBookmarkViewModel

        createBookmarkViewModel.eventCompleteCreateFromBookmark.observe(viewLifecycleOwner, Observer { finish ->
            if (finish) {
                findNavController().navigate(CreateBookmarkFragmentDirections.actionCreateBookmarkFragmentToBookmarkFragment())
                createBookmarkViewModel.onEventCompleteFromBookmark()
            }
        })

        createBookmarkViewModel.eventCompleteCreateFromAddBookmark.observe(viewLifecycleOwner, Observer { finish ->
            if (finish) {
                findNavController().navigate(CreateBookmarkFragmentDirections.actionCreateBookmarkFragmentToAddBookmarkFragment(createBookmarkViewModel.savedId.value!!))
//                findNavController().popBackStack()
            }
        })

        createBookmarkViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().navigate(CreateBookmarkFragmentDirections.actionCreateBookmarkFragmentToBookmarkFragment())
                createBookmarkViewModel.onBackComplete()
            }
        })

        return binding.root
    }


}