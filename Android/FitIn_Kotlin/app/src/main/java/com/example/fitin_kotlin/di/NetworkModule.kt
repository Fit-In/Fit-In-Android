package com.example.fitin_kotlin.di

import com.example.fitin_kotlin.data.local.EncryptedSharedPreferenceController
import com.example.fitin_kotlin.data.remote.api.*
import com.example.fitin_kotlin.data.repository.*
//import com.example.fitin_kotlin.network.AuthInterceptor
//import com.example.fitin_kotlin.network.AuthInterceptor
//import com.example.fitin_kotlin.network.AuthInterceptor
import com.example.fitin_kotlin.network.TokenAuthenticator
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
    private val gson : Gson = GsonBuilder()
        .setLenient()
        .create()

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
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
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

    @Singleton
    @Provides
    fun provideRecruitmentService(retrofit: Retrofit): RecruitmentService = retrofit.create(RecruitmentService::class.java)

    @Singleton
    @Provides
    fun provideRecruitmentRepository(recruitmentService: RecruitmentService) = RecruitmentRepository(recruitmentService)

    @Singleton
    @Provides
    fun provideBookmarkService(retrofit: Retrofit): BookmarkService = retrofit.create(BookmarkService::class.java)

    @Singleton
    @Provides
    fun provideBookmarkRepository(bookmarkService: BookmarkService) = BookmarkRepository(bookmarkService)

    @Singleton
    @Provides
    fun provideSaveBookmarkService(retrofit: Retrofit): SaveBookmarkService = retrofit.create(SaveBookmarkService::class.java)

    @Singleton
    @Provides
    fun provideSaveBookmarkRepository(saveBookmarkService: SaveBookmarkService) = SaveBookmarkRepository(saveBookmarkService)

//    @AuthInterceptorOkHttpClient
//    @Singleton
//    @Provides
//    fun provideAuthInterceptor(prefs: EncryptedSharedPreferenceController) : AuthInterceptor {
//        return AuthInterceptor(prefs)
//    }
}