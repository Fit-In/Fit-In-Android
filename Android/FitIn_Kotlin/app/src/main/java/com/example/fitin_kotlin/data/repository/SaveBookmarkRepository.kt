package com.example.fitin_kotlin.data.repository

import com.example.fitin_kotlin.data.model.network.request.RequestNewsBookmark
import com.example.fitin_kotlin.data.model.network.request.RequestRecruitmentBookmark
import com.example.fitin_kotlin.data.remote.api.SaveBookmarkService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveBookmarkRepository @Inject constructor(private val saveBookmarkService: SaveBookmarkService, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend fun saveNewsBookmark(requestNewsBookmark: RequestNewsBookmark) = withContext(ioDispatcher) { saveBookmarkService.saveNewsBookmark(requestNewsBookmark) }

    suspend fun saveRecruitmentBookmark(requestRecruitmentBookmark: RequestRecruitmentBookmark) = withContext(ioDispatcher) { saveBookmarkService.saveRecruitmentBookmark(requestRecruitmentBookmark) }

    suspend fun getSaveNews(saveNewsId: Long) = withContext(ioDispatcher) { saveBookmarkService.getSaveNews(saveNewsId) }

    suspend fun getSaveRecruitment(saveRecruitmentId: Long) = withContext(ioDispatcher) { saveBookmarkService.getSaveRecruitment(saveRecruitmentId) }

}