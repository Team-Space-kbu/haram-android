package com.space.haram_android.repository.token

import android.content.SharedPreferences
import com.space.haram_android.data.response.LoginRes
import com.space.haram_android.service.RefreshService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val refreshService: RefreshService
) : TokenRepository {

    override fun getAccessToken(): String? {
        return sharedPreferences.getString("accessToken", null)
    }

    override fun setToken(loginToken: LoginRes) = with(sharedPreferences) {
        loginToken.let {
            edit().putString("accessToken", it.accessToken).apply()
            edit().putString("refreshToken", it.refreshToken).apply()
        }
    }

    override suspend fun updateAccessToken() {
        refreshService.updateAccessToken(getRefreshToken()).let {
            sharedPreferences.edit().putString("accessToken", it.data.accessToken).apply()
        }
    }

    private fun getRefreshToken(): String? {
        return sharedPreferences.getString("refreshToken", null)
    }
}