package com.space.repository.di.retrofit

import com.space.shared.model.RefreshModel
import com.space.repository.di.token.AuthManager
import com.space.repository.di.token.TokenManager
import com.space.repository.service.inf.AuthService
import com.space.shared.data.auth.AuthStatus.*
import com.space.shared.data.auth.AuthToken
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber


class AuthAuthenticator(
    private val tokenManager: TokenManager,
    private val authManager: AuthManager,
    private val authService: AuthService
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            val token = authService.getToken(
                tokenManager.getRefreshToken(),
                RefreshModel(authManager.getUserId())
            )
            tokenManager.deleteToken()
            when (token.status) {
                PASS -> {
                    return@runBlocking setToken(response, token.authToken!!)
                }

                EXPIRATION -> {
                    val newToken = authService.login(authManager.getLoginModel())
                    if (newToken.status == PASS) {
                        return@runBlocking setToken(response, newToken.authToken!!)

                    }
                }

                else -> {}
            }
            Timber.i("Error!! Clear user data!!")
            authManager.deleteLogin()
            return@runBlocking null
        }
    }

    private fun setToken(response: Response, authToken: AuthToken): Request {
        authToken.let {
            tokenManager.setToken(it)
            return response.request.newBuilder().header("Authorization", "Bearer ${it.accessToken}")
                .build()
        }
    }

}