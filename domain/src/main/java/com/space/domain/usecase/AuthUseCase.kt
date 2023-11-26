package com.space.domain.usecase

import SpaceBody
import model.LoginModel
import data.auth.AuthToken
import com.space.domain.service.AuthService
import com.space.repository.di.token.AuthManager
import com.space.repository.di.token.TokenManager
import javax.inject.Inject


class AuthUseCase @Inject constructor(
    private val authService: AuthService,
    private val tokenManager: TokenManager,
    private val authManager: AuthManager
)  {
    suspend fun getSpaceAuthToken(loginModel: LoginModel): SpaceBody<AuthToken> {
        return authService.getLogin(loginModel).body()!!
    }

    fun setToken(authToken: AuthToken) {
        tokenManager.setToken(authToken)
    }

    fun getToken(): String? {
        return tokenManager.getAccessToken()
    }

    fun saveLogin(loginModel: LoginModel){
        return authManager.saveLoginModel(loginModel)
    }

}
