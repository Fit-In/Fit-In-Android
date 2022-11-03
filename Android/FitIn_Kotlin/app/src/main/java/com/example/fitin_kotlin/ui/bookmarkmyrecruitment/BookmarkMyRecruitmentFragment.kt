package com.example.fitin_kotlin.ui.bookmarkmyrecruitment

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
import com.example.fitin_kotlin.databinding.FragmentBookmarkMyRecruitmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkMyRecruitmentFragment : Fragment() {

    private val bookmarkMyRecruitmentViewModel: BookmarkMyRecruitmentViewModel by viewModels()
    private lateinit var bookmarkMyRecruitmentAdapter: BookmarkMyRecruitmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBookmarkMyRecruitmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark_my_recruitment, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.bookmarkMyRecruitmentViewModel = bookmarkMyRecruitmentViewModel

        bookmarkMyRecruitmentAdapter = BookmarkMyRecruitmentAdapter(BookmarkMyRecruitmentAdapter.OnClickListener {
            bookmarkMyRecruitmentViewModel.displaySavedRecruit(it)
        })

        binding.rvMyRecruitment.adapter = bookmarkMyRecruitmentAdapter

        bookmarkMyRecruitmentViewModel.eventBookmarkMyNews.observe(viewLifecycleOwner, Observer { news ->
            if (news) {
                findNavController().navigate(BookmarkMyRecruitmentFragmentDirections.actionBookmarkMyRecruitmentFragmentToBookmarkMyNewsFragment(bookmarkMyRecruitmentViewModel.bookmarkId.value!!))
                bookmarkMyRecruitmentViewModel.onEventBookmarkMyNewsComplete()
            }
        })

        bookmarkMyRecruitmentViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().popBackStack()
                bookmarkMyRecruitmentViewModel.onBackComplete()
            }
        })

        bookmarkMyRecruitmentViewModel.requestSavedRecruit.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                findNavController().navigate(BookmarkMyRecruitmentFragmentDirections.actionBookmarkMyRecruitmentFragmentToMyRecruitmentFragment(it.saveId))
                bookmarkMyRecruitmentViewModel.displaySavedRecruitFinish()
            }
        })

        return binding.root

    }

}