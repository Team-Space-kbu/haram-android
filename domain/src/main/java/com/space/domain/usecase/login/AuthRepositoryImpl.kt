package com.space.domain.usecase.login

import com.space.data.ResponseBody
import com.space.data.model.LoginModel
import com.space.data.res.LoginRes
import com.space.domain.service.AuthService
import com.space.domain.service.token.AuthManager
import com.space.domain.service.token.TokenManager
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val tokenManager: TokenManager,
    private val authManager: AuthManager
) : AuthRepository {
    override suspend fun getSpaceAuthToken(loginModel: LoginModel): ResponseBody<LoginRes> {
        return authService.getLogin(loginModel)
    }

    override fun setToken(loginRes: LoginRes) {
        tokenManager.setToken(loginRes)
    }

    override fun getToken(): String? {
        return tokenManager.getAccessToken()
    }

    override fun saveLogin(loginModel: LoginModel){
        return authManager.saveLoginModel(loginModel)
    }

}
