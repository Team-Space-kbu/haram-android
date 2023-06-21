package com.space.haram_android.di.network

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
                if (isSuccessful && body()!!.code == "PA01") {
                    tokenManager.setToken(newToken.body()!!.data)
                    return@runBlocking addHeader(response, body()!!.data.accessToken)
                }
                tokenManager.deleteToken()
                val loginModel: LoginModel = authManager.getLoginModel()
                val newLogin = getNewLogin(loginModel)
                newLogin.run {
                    tokenManager.setToken(this.data)
                    return@runBlocking addHeader(response, body()!!.data.accessToken)
                }
            }
        }
    }

    private fun addHeader(response: Response, token: String) =
        response.request.newBuilder().header("Authorization", "Bearer $token").build()

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