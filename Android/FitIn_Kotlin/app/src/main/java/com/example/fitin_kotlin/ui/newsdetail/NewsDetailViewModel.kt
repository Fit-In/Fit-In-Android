package com.example.fitin_kotlin.ui.newsdetail

import androidx.lifecycle.*
import com.example.fitin_kotlin.data.model.network.response.ResponseNews
import com.example.fitin_kotlin.data.model.network.response.ResponseNewsList
import com.example.fitin_kotlin.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    state: SavedStateHandle
) : ViewModel(){

    private val requestNews = MutableLiveData<ResponseNewsList>()
    private val _news = MutableLiveData<ResponseNews>()
    val news: LiveData<ResponseNews>
        get() = _news

    init {
        requestNews.value = state.getLiveData<ResponseNewsList>("selectedNews").value
        getNews()
    }

    private fun getNews() {
        val newsId: Long? = requestNews.value?.id
        viewModelScope.launch {
            _news.value = newsRepository.getNews(newsId!!)
        }
    }
}