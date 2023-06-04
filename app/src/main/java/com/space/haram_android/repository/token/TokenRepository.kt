package com.space.haram_android.repository.token

import com.space.haram_android.data.response.LoginRes

interface TokenRepository {

    fun getAccessToken(): String?

    fun setToken(loginToken: LoginRes)

    suspend fun updateAccessToken()
}