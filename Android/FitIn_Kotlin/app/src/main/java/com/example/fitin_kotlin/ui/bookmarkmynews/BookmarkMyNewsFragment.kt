package com.example.fitin_kotlin.ui.bookmarkmynews

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
import com.example.fitin_kotlin.data.model.network.response.ResponseBookmark
import com.example.fitin_kotlin.data.model.network.response.ResponseSavedBookmark
import com.example.fitin_kotlin.databinding.FragmentBookmarkMyNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkMyNewsFragment : Fragment() {

    private val bookmarkMyNewsViewModel: BookmarkMyNewsViewModel by viewModels()
    private lateinit var bookmarkMyNewsAdapter: BookmarkMyNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentBookmarkMyNewsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark_my_news, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.bookmarkMyNewsViewModel = bookmarkMyNewsViewModel

        // TODO 상세화면 넘어가게 RecyclerView 클릭 리스너 이벤트 처리하기(클릭한 Dto 가져와서 id 값 넘겨서 상세화면 나오게 처리함)
        bookmarkMyNewsAdapter = BookmarkMyNewsAdapter(BookmarkMyNewsAdapter.OnClickListener {
            bookmarkMyNewsViewModel.displaySavedNews(it)
        })

        binding.rvMyNews.adapter = bookmarkMyNewsAdapter

        bookmarkMyNewsViewModel.myNews.observe(viewLifecycleOwner) {
            if (it.any { it.title != null}) {
                binding.tvEmptyBookmark.isVisible = false
                binding.rvMyNews.isVisible = true
            }
        }

        // TODO 상세화면으로 넘어가게 id를 Navigation으로 넘겨서 saveDB에 저장된 id의 값을 보여지게 처리함
        bookmarkMyNewsViewModel.eventBookmarkMyRecruitment.observe(viewLifecycleOwner, Observer { recruit ->
            if (recruit) {
                findNavController().navigate(BookmarkMyNewsFragmentDirections.actionBookmarkMyNewsFragmentToBookmarkMyRecruitmentFragment(
                    bookmarkMyNewsViewModel.bookmark.value!!))
                bookmarkMyNewsViewModel.onEventBookmakMyRecruitComplete()
            }
        })

        bookmarkMyNewsViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().popBackStack()
                bookmarkMyNewsViewModel.onBackComplete()
            }
        })

        bookmarkMyNewsViewModel.requestSavedNews.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                findNavController().navigate(BookmarkMyNewsFragmentDirections.actionBookmarkMyNewsFragmentToMyNewsFragment(it.saveId))
                bookmarkMyNewsViewModel.displaySavedNewsFinish()
            }
        })

        return binding.root
    }

}