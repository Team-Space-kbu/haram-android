package com.space.domain.di.network

import android.util.Log
import com.space.data.ResponseBody
import com.space.data.model.LoginModel
import com.space.data.model.RefreshModel
import com.space.data.response.LoginRes
import com.space.domain.service.AuthService
import com.space.domain.service.token.AuthManager
import com.space.domain.service.token.TokenManager
import com.space.shared.annotation.SpaceLoginModule
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val authManager: AuthManager,
    @SpaceLoginModule private val retrofit: Retrofit

) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            val newToken = getNewToken(tokenManager.getRefreshToken(), authManager.getUserId())
            newToken.run {
                if (isSuccessful && body()!!.code == "PA01") {
                    body()?.let {
                        Timber.i("Token Refresh!!")
                        tokenManager.setToken(it.data)
                        return@runBlocking addHeader(response, it.data.accessToken)
                    }
                }
                if (code() == 402 || (code() == 200 && body()!!.code == "TK03")) {
                    Timber.d("Refresh expire!!")
                    val loginModel: LoginModel = authManager.getLoginModel()
                    if (!loginModel.userId.isNullOrBlank()) {
                        tokenManager.deleteToken()
                        val newLogin = getNewLogin(loginModel)
                        newLogin.run {
                            tokenManager.setToken(data)
                            data.let {
                                return@runBlocking addHeader(response, data.accessToken)
                            }
                        }
                    }
                }
                Log.d("Authenticator", "Clear user info!!")
                authManager.deleteLogin()
                tokenManager.deleteToken()
                null
            }
        }
    }

    private fun addHeader(response: Response, token: String) =
        response.request.newBuilder().header("Authorization", "Bearer $token").build()

    private suspend fun getNewToken(
        refreshToken: String?,
        userId: String?
    ): retrofit2.Response<ResponseBody<LoginRes>> {
        val service = retrofit.create(AuthService::class.java)
        return service.updateAccessToken("Bearer $refreshToken", RefreshModel(userId = userId))
    }

    private suspend fun getNewLogin(
        loginModel: LoginModel
    ): ResponseBody<LoginRes> {
        val service = retrofit.create(AuthService::class.java)
        return service.getLogin(loginModel)
    }
}