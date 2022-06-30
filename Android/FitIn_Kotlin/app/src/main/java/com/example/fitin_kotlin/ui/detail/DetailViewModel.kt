package com.example.fitin_kotlin.ui.detail

import androidx.lifecycle.*
import com.example.fitin_kotlin.data.model.network.response.ResponseNews
import com.example.fitin_kotlin.data.model.network.response.ResponseNewsList
import com.example.fitin_kotlin.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    state: SavedStateHandle
) : ViewModel(){

    private val requestNews = MutableLiveData<ResponseNewsList>()
    lateinit var news: LiveData<ResponseNews>

    init {
        requestNews.value = state.getLiveData<ResponseNewsList>("selectedNews").value

        val newsId: Long? = requestNews.value?.id

        viewModelScope.launch {
            news = newsRepository.getNews(newsId!!)
        }
    }
}