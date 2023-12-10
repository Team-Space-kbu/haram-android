package com.space.domain.usecase

import com.space.shared.SpaceBody
import com.space.shared.model.LoginModel
import com.space.shared.data.auth.AuthToken
import com.space.repository.api.AuthApi
import com.space.repository.di.token.AuthManager
import com.space.repository.di.token.TokenManager
import javax.inject.Inject


class AuthUseCase @Inject constructor(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager,
    private val authManager: AuthManager
)  {
    suspend fun getSpaceAuthToken(loginModel: LoginModel): SpaceBody<AuthToken> {
        return authApi.getLogin(loginModel).body()!!
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
