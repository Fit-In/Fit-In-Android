package com.example.fitin_kotlin.data.repository

import android.util.Log
import com.example.fitin_kotlin.data.model.network.request.RequestPhoneNumber
import com.example.fitin_kotlin.data.model.network.request.RequestSignIn
import com.example.fitin_kotlin.data.model.network.request.RequestSignUp
import com.example.fitin_kotlin.data.model.network.request.RequestTokenReissue
import com.example.fitin_kotlin.data.remote.api.UserService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO){

    suspend fun postSignUp(requestSignUp: RequestSignUp) = withContext(ioDispatcher) { userService.postSignUp(requestSignUp) }

    suspend fun getEmailDuplicateCheck(email: String) = withContext(ioDispatcher) {userService.getEmailDuplicateCheck(email) }

    suspend fun postSignIn(requestSignIn: RequestSignIn) = withContext(ioDispatcher) { userService.postSignIn(requestSignIn) }

    fun postReIssue(requestTokenReissue: RequestTokenReissue) = userService.postReIssue(requestTokenReissue)

    suspend fun postCoolSms(requestPhoneNumber: RequestPhoneNumber) = withContext(ioDispatcher) { userService.postCoolSms(requestPhoneNumber) }
}