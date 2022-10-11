package com.example.fitin_kotlin.util

object PreferencesKey {

    private const val encryptedSharedPreferencesName = "encryptedSharedPrefs"
    private const val accessToken = "FitInAccessToken"
    private const val refreshToken = "FitInRefreshToken"
    private const val userEmail = "UserEmail"

    fun provideEncryptedSharedPrefsName(): String = encryptedSharedPreferencesName

    fun provideAccessToken(): String = accessToken

    fun provideRefreshToken(): String = refreshToken

    fun provideUserEmail(): String = userEmail
}