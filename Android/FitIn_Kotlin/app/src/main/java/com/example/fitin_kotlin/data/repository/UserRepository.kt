package com.example.fitin_kotlin.data.repository

import android.util.Log
import com.example.fitin_kotlin.data.model.network.request.RequestSignIn
import com.example.fitin_kotlin.data.model.network.request.RequestSignUp
import com.example.fitin_kotlin.data.model.network.request.RequestTokenReissue
import com.example.fitin_kotlin.data.remote.api.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService){

    suspend fun postSignUp(requestSignUp: RequestSignUp) = userService.postSignUp(requestSignUp)

    suspend fun postSignIn(requestSignIn: RequestSignIn) = userService.postSignIn(requestSignIn)

    suspend fun postReIssue(requestTokenReissue: RequestTokenReissue) = withContext(Dispatchers.IO) {userService.getReIssue(requestTokenReissue) }

    suspend fun getEmail(accessToken: String) = userService.getEmail("Bearer $accessToken")

}