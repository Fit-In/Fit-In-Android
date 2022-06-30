package com.example.fitin_kotlin.data.repository

import com.example.fitin_kotlin.data.remote.api.NewsService
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService) {

    suspend fun callNews() = newsService.callNews()

    suspend fun getNewsList() = newsService.getNewsList()

    suspend fun getNews(newsId: Long) = newsService.getNews(newsId)
}