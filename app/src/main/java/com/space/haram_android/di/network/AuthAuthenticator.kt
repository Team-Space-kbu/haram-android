package com.space.haram_android.di.network

import com.space.haram_android.common.data.response.LoginRes
import com.space.haram_android.common.token.TokenManager
import com.space.haram_android.repository.ResponseBody
import com.space.haram_android.service.AuthService
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager, private val retrofit: Retrofit
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getRefreshToken()
        }
        return runBlocking {
            val newToken = getNewToken(token)

            if (!newToken.isSuccessful || newToken.body() == null) {
                tokenManager.deleteToken()
            }

            newToken.body()?.let {
                if (newToken.code() == 200 && newToken.body()!!.code == "TK03") {
                    tokenManager.deleteToken()
                    response.request
                        .newBuilder()
                        .build()
                } else {
                    tokenManager.setToken(it.data)
                    response.request
                        .newBuilder()
                        .header("accessToken", it.data.accessToken).build()
                }
            }
        }

    }

    private suspend fun getNewToken(
        refreshToken: String?
    ): retrofit2.Response<ResponseBody<LoginRes>> {
        val service = retrofit.create(AuthService::class.java)
        return service.updateAccessToken(refreshToken)
    }
}