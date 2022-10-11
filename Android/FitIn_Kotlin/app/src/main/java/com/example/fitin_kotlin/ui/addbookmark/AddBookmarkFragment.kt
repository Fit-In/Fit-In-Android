package com.example.fitin_kotlin.ui.addbookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fitin_kotlin.R
import com.example.fitin_kotlin.databinding.FragmentAddBookmarkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBookmarkFragment : Fragment() {

    private val addBookmarkViewModel: AddBookmarkViewModel by viewModels()
    private lateinit var addBookmarkAdapter: AddBookmarkAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAddBookmarkBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_bookmark, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.addBookmarkViewModel = addBookmarkViewModel

        addBookmarkViewModel.bookmarkList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.tvEmptyBookmark.isVisible = false
                binding.rvBookmarkList.isVisible = true
            }
        }

        addBookmarkAdapter = AddBookmarkAdapter(AddBookmarkAdapter.OnClickListener {
            addBookmarkViewModel.addBookmark(it)
        })

        binding.rvBookmarkList.adapter = addBookmarkAdapter

        addBookmarkViewModel.toastMessage.observe(viewLifecycleOwner, Observer { toast ->
            Toast.makeText(requireContext(), toast, Toast.LENGTH_SHORT).show()
        })

        addBookmarkViewModel.eventAddComplete.observe(viewLifecycleOwner, Observer { add ->
            if (add) {
                findNavController().popBackStack()
            }
        })

        addBookmarkViewModel.eventCreateBookmark.observe(viewLifecycleOwner, Observer { addBookmark ->
            if (addBookmark != null) {
                findNavController().navigate(AddBookmarkFragmentDirections.actionAddBookmarkFragmentToCreateBookmarkFragment(addBookmark))
                addBookmarkViewModel.onCreateBookmarkComplete()
            }
        })

        return binding.root
    }

}