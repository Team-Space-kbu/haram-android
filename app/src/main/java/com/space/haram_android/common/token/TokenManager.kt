package com.space.haram_android.common.token

import android.content.SharedPreferences
import com.space.haram_android.common.annotation.TokenEncrypted
import com.space.haram_android.common.data.response.LoginRes
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class TokenManager @Inject constructor(
    @TokenEncrypted private val sharedPreferences: SharedPreferences
) {

    fun getAccessToken(): String? {
        return sharedPreferences.getString("accessToken", null)
    }

    fun setToken(loginToken: LoginRes) {
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