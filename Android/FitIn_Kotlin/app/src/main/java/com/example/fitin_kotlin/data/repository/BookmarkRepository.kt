package com.example.fitin_kotlin.data.repository

import com.example.fitin_kotlin.data.model.network.request.RequestCreateBookmark
import com.example.fitin_kotlin.data.remote.api.BookmarkService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookmarkRepository @Inject constructor(private val bookmarkService: BookmarkService, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend fun getBookmarkList(email: String) = withContext(ioDispatcher) { bookmarkService.getBookmarkList(email) }

    suspend fun createBookmark(requestCreateBookmark: RequestCreateBookmark) = withContext(ioDispatcher) { bookmarkService.createBookmark(requestCreateBookmark) }

    suspend fun addBookmark(requestCreateBookmark: RequestCreateBookmark) = withContext(ioDispatcher) { bookmarkService.addBookmark(requestCreateBookmark) }

    suspend fun findBookmark(id: Long) = withContext(ioDispatcher) { bookmarkService.findBookmark(id) }

}