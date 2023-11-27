package com.space.repository.di.token

import android.content.SharedPreferences
import com.space.shared.data.auth.AuthToken
import com.space.shared.annotation.TokenEncrypted
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class TokenManager @Inject constructor(
    @TokenEncrypted private val sharedPreferences: SharedPreferences
) {

    fun getAccessToken(): String? {
        return sharedPreferences.getString("accessToken", null)
    }

    fun setToken(loginToken: AuthToken) {
        loginToken.let {
            sharedPreferences.edit().putString("accessToken", it.accessToken).apply()
            sharedPreferences.edit().putString("refreshToken", it.refreshToken).apply()
        }
    }

    fun deleteToken() {
        runBlocking {
            sharedPreferences.edit().clear().commit()
        }
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString("refreshToken", null)
    }


}