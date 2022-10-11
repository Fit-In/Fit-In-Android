package com.example.fitin_kotlin.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fitin_kotlin.R
import com.example.fitin_kotlin.databinding.FragmentBookmarkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() {

    private val bookmarkViewModel: BookmarkViewModel by viewModels()
    private lateinit var bookmarkAdapter: BookmarkAdapter

    // 북마크 상세화면이 구현되어 있지 않기 때문에 아직 클릭 이벤트 처리는 하지 않음

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentBookmarkBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.bookmarkViewModel = bookmarkViewModel

        bookmarkViewModel.bookmarkList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.tvEmptyBookmark.isVisible = false
                binding.rvBookmarkList.isVisible = true
            }
        }

        bookmarkAdapter = BookmarkAdapter(BookmarkAdapter.OnClickListener {

        })

        binding.rvBookmarkList.adapter = bookmarkAdapter

        bookmarkViewModel.eventCreateBookmark.observe(viewLifecycleOwner, Observer { bookmark ->
            if (bookmark != null) {
                findNavController().navigate(BookmarkFragmentDirections.actionBookmarkFragmentToCreateBookmarkFragment(bookmark))
                bookmarkViewModel.onCreateBookmarkComplete()
            }
        })

        bookmarkViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().navigate(BookmarkFragmentDirections.actionBookmarkFragmentToHomeFragment())
                bookmarkViewModel.onBackComplete()
            }
        })

        return binding.root
    }


}