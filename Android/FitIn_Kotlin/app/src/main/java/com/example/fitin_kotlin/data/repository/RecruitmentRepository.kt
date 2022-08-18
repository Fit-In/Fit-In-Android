package com.example.fitin_kotlin.data.repository

import com.example.fitin_kotlin.data.remote.api.RecruitmentService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecruitmentRepository @Inject constructor(private val recruitmentService: RecruitmentService, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend fun callRecruitment() = withContext(ioDispatcher) { recruitmentService.callRecruitment() }

    suspend fun getRecruitmentList() = withContext(ioDispatcher) { recruitmentService.getRecruitmentList() }

    suspend fun getRecruitment(recruitmentId: Long) = withContext(ioDispatcher) { recruitmentService.getRecruitment(recruitmentId) }
}