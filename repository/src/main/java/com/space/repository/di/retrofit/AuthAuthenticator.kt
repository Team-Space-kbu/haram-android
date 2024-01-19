package com.space.repository.di.retrofit

import com.space.shared.model.RefreshModel
import com.space.repository.di.token.AuthManager
import com.space.repository.di.token.TokenManager
import com.space.repository.service.auth.AuthService
import com.space.shared.data.auth.AuthStatus.*
import com.space.shared.data.auth.AuthToken
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber
import javax.inject.Inject

internal class AuthAuthenticator @Inject constructor(
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

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {
    @Binds
    abstract fun bindAuthAuthenticator(
        authAuthenticator: AuthAuthenticator
    ): Authenticator
}