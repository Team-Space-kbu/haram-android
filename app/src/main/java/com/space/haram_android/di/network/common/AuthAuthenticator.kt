package com.space.haram_android.di.network.common

import com.space.haram_android.common.annotation.SpaceLoginModule
import com.space.haram_android.common.data.model.LoginModel
import com.space.haram_android.common.data.response.LoginRes
import com.space.haram_android.common.token.AuthManager
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
    private val tokenManager: TokenManager,
    private val authManager: AuthManager,
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
                        .header("Authorization", "Bearer ${newToken.body()!!.data.accessToken}")
                        .build()
                }
                tokenManager.deleteToken()
                val loginModel: LoginModel = authManager.getLoginModel()
                val newLogin = getNewLogin(loginModel)
                newLogin.run {
                    tokenManager.setToken(this.data)
                    return@runBlocking response.request.newBuilder()
                        .header("Authorization", "Bearer ${newToken.body()!!.data.accessToken}")
                        .build()
                }
            }
        }
    }

    private suspend fun getNewToken(
        refreshToken: String?
    ): retrofit2.Response<ResponseBody<LoginRes>> {
        val service = retrofit.create(AuthService::class.java)
        return service.updateAccessToken("Bearer $refreshToken")
    }

    private suspend fun getNewLogin(
        loginModel: LoginModel
    ): ResponseBody<LoginRes> {
        val service = retrofit.create(AuthService::class.java)
        return service.getLogin(loginModel)
    }
}