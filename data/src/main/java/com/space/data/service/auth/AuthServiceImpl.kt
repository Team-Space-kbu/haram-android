package com.space.data.service.auth

import com.space.data.rest.AuthApi
import com.space.shared.data.auth.Auth
import com.space.shared.data.auth.AuthStatus.*
import com.space.shared.model.LoginModel
import com.space.shared.model.RefreshModel
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

internal class AuthServiceImpl @Inject constructor(
    private val authApi: AuthApi,
) : AuthService {
    override suspend fun getToken(
        refreshToken: String?,
        userId: RefreshModel
    ): Auth {
        return runBlocking {
            val token = authApi.updateAccessToken("Bearer $refreshToken", userId)
            if (token.isSuccessful) {
                Timber.i("Token Refresh!!")
                return@runBlocking Auth(PASS, token.body()!!.data)
            }
            if (token.code() == 402) {
                Timber.i("Refresh expire!!")
                return@runBlocking Auth(EXPIRATION)
            }
            Timber.i("Login Error!!")
            Auth(ERROR)
        }
    }

    override suspend fun login(loginModel: LoginModel): Auth {
        return runBlocking {
            val login = authApi.getLogin(loginModel)
            return@runBlocking when (login.code()) {
                in 200..299 -> {
                    Auth(PASS, login.body()!!.data)
                }

                in 400..499 -> Auth(FAIL)

                else -> Auth(ERROR)
            }

        }
    }
}