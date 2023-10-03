package com.space.domain.usecase.login

import com.space.data.ResponseBody
import com.space.data.model.LoginModel
import com.space.data.res.LoginRes
import com.space.domain.service.AuthService
import com.space.repository.token.AuthManager
import com.space.repository.token.TokenManager
import javax.inject.Inject


class AuthUseCase @Inject constructor(
    private val authService: AuthService,
    private val tokenManager: TokenManager,
    private val authManager: AuthManager
)  {
    suspend fun getSpaceAuthToken(loginModel: LoginModel): ResponseBody<LoginRes> {
        return authService.getLogin(loginModel).body()!!
    }

    fun setToken(loginRes: LoginRes) {
        tokenManager.setToken(loginRes)
    }

    fun getToken(): String? {
        return tokenManager.getAccessToken()
    }

    fun saveLogin(loginModel: LoginModel){
        return authManager.saveLoginModel(loginModel)
    }

}
