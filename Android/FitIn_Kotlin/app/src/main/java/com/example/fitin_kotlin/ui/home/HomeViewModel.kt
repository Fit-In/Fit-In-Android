package com.example.fitin_kotlin.ui.home

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.data.model.network.response.ResponseNews
import com.example.fitin_kotlin.data.model.network.response.ResponseNewsList
import com.example.fitin_kotlin.data.repository.NewsRepository
import com.example.fitin_kotlin.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel(){

    private val _newsList = MutableLiveData<List<ResponseNewsList>>()
    val newsList: LiveData<List<ResponseNewsList>>
        get() = _newsList

    val requestNews: MutableLiveData<ResponseNewsList?> = MutableLiveData<ResponseNewsList?>()

    init {
        getNewsList()
    }

    private fun getNewsList() {
        viewModelScope.launch {
            _newsList.value = newsRepository.getNewsList()
        }
    }

    fun displayNews(responseNewsList: ResponseNewsList) {
        requestNews.value = responseNewsList
    }

    fun displayNewsFinish() {
        requestNews.value = null
    }

}