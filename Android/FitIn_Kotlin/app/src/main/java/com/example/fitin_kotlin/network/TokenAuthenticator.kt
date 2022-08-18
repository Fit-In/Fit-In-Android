package com.example.fitin_kotlin.network

import android.util.Log
import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.data.model.network.request.RequestTokenReissue
import com.example.fitin_kotlin.data.model.network.response.ResponseToken
import com.example.fitin_kotlin.data.repository.UserRepository
import dagger.Lazy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TokenAuthenticator @Inject constructor(
    private val prefs: EncryptedSharedPreferenceController,
    private val repository: Lazy<UserRepository>
) : Authenticator {
//
//    val accessToken: String? = prefs.getAccessToken()
//    val refreshToken: String? = prefs.getRefreshToken()


    override fun authenticate(route: Route?, response: Response): Request? {

        if (response.responseCount >= 2) {
            return null
        }

//        val accessToken: String? = prefs.getAccessToken()
//        val refreshToken: String? = prefs.getRefreshToken()



        val requestTokenReissue =
            RequestTokenReissue(
                prefs.getAccessToken().toString(),
                prefs.getRefreshToken().toString()
            )
        val token: retrofit2.Response<ResponseToken> =
            repository.get().postReIssue(requestTokenReissue).execute()
        if (token.isSuccessful) {

            prefs.setAccessToken(token.body()!!.accessToken)
            prefs.setRefreshToken(token.body()!!.refreshToken)

            Log.e("Token", "token ${prefs.getAccessToken().toString()}")

            return response.request.newBuilder()
                .header("Authorization", "Bearer ${token.body()?.accessToken}")
                .build()
        } else {
            return null
        }

        return null



//        if (accessToken != null) {
//            return response.request.newBuilder()
//                .header("Authorization", "Bearer ${accessToken}")
//                .build()
//        } else return null
    }

    private val Response.responseCount: Int
        get() = generateSequence(this) { it.priorResponse }.count()

}


