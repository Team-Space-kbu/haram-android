package com.space.haram_android.di.network.common

import com.space.haram_android.common.annotation.SpaceLoginModule
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
import java.io.IOException
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    @SpaceLoginModule private val retrofit: Retrofit
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request {
        return runBlocking {
            val token = tokenManager.getRefreshToken()
            val newToken = getNewToken(token)
            newToken.run {
                this.body()?.data?.let {
                    tokenManager.setToken(newToken.body()!!.data)
                    return@runBlocking response.request.newBuilder()
                        .header("accessToken", newToken.body()!!.data.accessToken)
                        .build()
                }
                tokenManager.deleteToken()
                throw IOException("Token refresh failed!! status code = ${this.code()}, server body code =  ${this.body()?.code}")
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