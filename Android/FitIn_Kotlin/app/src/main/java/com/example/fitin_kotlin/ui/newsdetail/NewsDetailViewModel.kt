package com.example.fitin_kotlin.ui.newsdetail

import android.view.View
import androidx.lifecycle.*
import com.example.fitin_kotlin.data.model.network.response.ResponseNews
import com.example.fitin_kotlin.data.model.network.response.ResponseNewsList
import com.example.fitin_kotlin.data.model.network.response.asRequestNewsBookmark
import com.example.fitin_kotlin.data.repository.NewsRepository
import com.example.fitin_kotlin.data.repository.SaveBookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val saveBookmarkRepository: SaveBookmarkRepository,
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

    val saveId: MutableLiveData<Long?> = MutableLiveData<Long?>()

    fun onAddBookmark(view: View) {
        val requestNewsBookmark = _news.value?.asRequestNewsBookmark()
        viewModelScope.launch {
            val saveNews = saveBookmarkRepository.saveNewsBookmark(requestNewsBookmark!!)
            when (saveNews.isSuccessful) {
                true -> {
                    if (saveNews.body()!!.state == 200) {
                        val result = saveNews.body()?.data.toString().toDouble().toLong()
                        saveId.value = result
                    }
                }
                else -> {
                    saveId.value = 0
                }
            }

        }
    }

    fun onAddBookmarkComplete() {
        saveId.value = null
    }

    private val _eventBack = MutableLiveData<Boolean>()
    val eventBack: LiveData<Boolean>
        get() = _eventBack

    fun onBack() {
        _eventBack.value = true
    }

    fun onBackComplete() {
        _eventBack.value = false
    }
}