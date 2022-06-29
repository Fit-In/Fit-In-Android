package com.example.fitin_kotlin.data.local

import android.content.SharedPreferences
import com.example.fitin_kotlin.util.PreferencesKey
import javax.inject.Inject

class EncryptedSharedPreferenceController @Inject constructor(private val preferences: SharedPreferences) {

    private val prefsEdit: SharedPreferences.Editor = preferences.edit()

    companion object {
        private val fitinAccessToken = PreferencesKey.provideAccessToken()
        private val fitinRefreshToken = PreferencesKey.provideRefreshToken()
    }

    fun setAccessToken(accessToken: String) {
        prefsEdit.putString(fitinAccessToken, accessToken).apply()
    }

    fun getAccessToken(): String? = preferences.getString(fitinAccessToken, "no")

    fun setRefreshToken(refreshToken: String) {
        prefsEdit.putString(fitinRefreshToken, refreshToken).apply()
    }

    fun getRefreshToken(): String? = preferences.getString(fitinRefreshToken, "no")

    fun deleteToken() {
        prefsEdit.remove(fitinAccessToken).remove(fitinRefreshToken).commit()
    }

}