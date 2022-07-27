package com.example.fitin_kotlin.di

import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.data.remote.api.NewsService
import com.example.fitin_kotlin.data.remote.api.UserService
import com.example.fitin_kotlin.data.repository.NewsRepository
import com.example.fitin_kotlin.data.repository.UserRepository
//import com.example.fitin_kotlin.network.AuthInterceptor
//import com.example.fitin_kotlin.network.AuthInterceptor
//import com.example.fitin_kotlin.network.AuthInterceptor
import com.example.fitin_kotlin.network.TokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://10.0.2.2:8080"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, tokenAuthenticator: TokenAuthenticator): OkHttpClient =
        OkHttpClient
            .Builder()
//            .authenticator(tokenAuthenticator)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun provideUserRepository(userService: UserService) = UserRepository(userService)

    @Singleton
    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService = retrofit.create(NewsService::class.java)

    @Singleton
    @Provides
    fun provideNewsRepository(newsService: NewsService) = NewsRepository(newsService)


//    @AuthInterceptorOkHttpClient
//    @Singleton
//    @Provides
//    fun provideAuthInterceptor(prefs: EncryptedSharedPreferenceController) : AuthInterceptor {
//        return AuthInterceptor(prefs)
//    }
}