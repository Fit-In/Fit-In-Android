package com.example.fitin_kotlin.ui.news

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
import com.example.fitin_kotlin.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var newsListAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.newsViewModel = newsViewModel

        newsListAdapter = NewsAdapter(NewsAdapter.OnClickListener {
            newsViewModel.displayNews(it)
        })

        binding.rvNewsList.adapter = newsListAdapter

        newsViewModel.newsList.observe(viewLifecycleOwner) {
            it?.let {
                newsListAdapter.responseNewsList = it
            }
        }

        binding.svSearchNews.isActivated = true
        binding.svSearchNews.onActionViewExpanded()
        binding.svSearchNews.clearFocus()

        binding.svSearchNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                (binding.rvNewsList.adapter as NewsAdapter).filter.filter(newText)
                (binding.rvNewsList.adapter as NewsAdapter).notifyDataSetChanged()
                return false
            }

        })

        newsViewModel.requestNews.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                findNavController().navigate(
                    NewsFragmentDirections.actionHomeFragmentToDetailFragment(it))
                newsViewModel.displayNewsFinish()
            }
        })

        newsViewModel.eventBack.observe(viewLifecycleOwner, Observer { back ->
            if (back) {
                findNavController().navigate(NewsFragmentDirections.actionNewsFragmentToHomeFragment())
                newsViewModel.onBackComplete()
            }
        })

        return binding.root
    }

}