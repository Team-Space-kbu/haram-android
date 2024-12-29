package com.space.data.di.retrofit

import com.space.data.service.auth.AuthService
import com.space.data.service.login.LoginService
import com.space.shared.data.auth.Auth
import com.space.shared.data.auth.AuthStatus.*
import com.space.shared.data.auth.AuthToken
import com.space.shared.exception.user.LogoutProcessed
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
import java.io.IOException
import javax.inject.Inject

internal class AuthAuthenticator @Inject constructor(
    private val loginService: LoginService,
    private val authService: AuthService
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            val token: Auth = loginService.getToken(
                authService.getRefreshToken(),
                authService.getAuthModel()
            )
            authService.deleteToken()
            when (token.status) {
                PASS -> {
                    return@runBlocking setToken(response, token.authToken!!)
                }

                EXPIRATION -> {
                    val newToken = loginService.login(authService.getLoginModel())
                    if (newToken.status == PASS) {
                        return@runBlocking setToken(response, newToken.authToken!!)
                    }
                }

                LOGOUT -> {
                    authService.deleteLogin()
                    throw IOException(LogoutProcessed("You have been logged out remotely from the server."))
                }

                else -> {}
            }
            Timber.i("Error!! Clear user data!!")
            authService.deleteLogin()
            return@runBlocking null
        }
    }

    private fun setToken(response: Response, authToken: AuthToken): Request {
        authToken.let {
            authService.saveToken(it)
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