package com.example.fitin_kotlin.network

import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.data.model.network.request.RequestTokenReissue
import com.example.fitin_kotlin.data.model.network.response.ResponseToken
import com.example.fitin_kotlin.data.remote.api.UserService
import com.example.fitin_kotlin.data.repository.UserRepository
import com.example.fitin_kotlin.di.NetworkModule
import dagger.Lazy
import kotlinx.coroutines.*
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class AuthInterceptorOkHttpClient

@Singleton
class AuthInterceptor @Inject constructor(private val prefs: EncryptedSharedPreferenceController, private val repository: Lazy<UserRepository>) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken: String? = prefs.getAccessToken()
        val refreshToken: String? = prefs.getRefreshToken()

        val original: Request = chain.request().newBuilder().header("Authorization",
            "Bearer $accessToken"
        ).build()
        var response: Response = chain.proceed(original)

        if (response.code == 401 && response.body!!.equals("null")) {
            runBlocking {
                val requestTokenReissue = RequestTokenReissue(accessToken.toString(), refreshToken.toString())
                val token: retrofit2.Response<ResponseToken> = repository.get().postReIssue(requestTokenReissue)
                withContext(Dispatchers.Main) {
                    if(token.isSuccessful) {
                        prefs.setAccessToken(token.body()!!.accessToken)
                        prefs.setRefreshToken(token.body()!!.refreshToken)
                        response = chain.proceed(original.newBuilder().header("Authorization", "Bearer " + token.body()!!.accessToken).build())
                    }
                    response = chain.proceed(original.newBuilder().header("Authorization", "Bearer " + prefs.getAccessToken()).build())
                }
            }
        }
        return response
    }
}