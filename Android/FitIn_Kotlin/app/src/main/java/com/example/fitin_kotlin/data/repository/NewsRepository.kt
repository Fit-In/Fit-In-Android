package com.example.fitin_kotlin.data.repository

import com.example.fitin_kotlin.data.remote.api.NewsService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend fun callNews() = withContext(ioDispatcher) { newsService.callNews() }

    suspend fun getNewsList() = withContext(ioDispatcher) { newsService.getNewsList() }

    suspend fun getNews(newsId: Long) = withContext(ioDispatcher) { newsService.getNews(newsId) }
}