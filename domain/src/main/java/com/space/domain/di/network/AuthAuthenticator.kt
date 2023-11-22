package com.space.domain.di.network

import com.space.data.ResponseBody
import com.space.data.model.LoginModel
import com.space.data.model.RefreshModel
import com.space.data.response.LoginRes
import com.space.domain.service.AuthService
import com.space.repository.token.AuthManager
import com.space.repository.token.TokenManager
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
            newToken.let { res ->
                tokenManager.deleteToken()
                if (res.isSuccessful) {
                    res.body()?.let { body ->
                        Timber.i("Token Refresh!!")
                        tokenManager.setToken(body.data)
                        return@runBlocking addHeader(response, body.data.accessToken)
                    }
                } else {
                    if (res.code() == 402) {
                        Timber.i("Refresh expire!!")
                        val newLogin = login(authManager.getLoginModel())
                        newLogin.let {
                            if (it.isSuccessful) {
                                tokenManager.setToken(it.body()!!.data)
                                return@runBlocking addHeader(
                                    response,
                                    it.body()!!.data.accessToken
                                )
                            }
                        }

                    }
                    Timber.i("Error!! Clear user data!!")
                    authManager.deleteLogin()
                    return@runBlocking null
                }
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

    private suspend fun login(
        loginModel: LoginModel
    ): retrofit2.Response<ResponseBody<LoginRes>> {
        val service = retrofit.create(AuthService::class.java)
        return service.getLogin(loginModel)
    }
}