package com.space.haram_android.repository.login

import com.space.haram_android.repository.ResponseBody
import com.space.haram_android.common.data.response.LoginRes
import com.space.haram_android.common.data.model.LoginModel
import com.space.haram_android.common.token.AuthManager
import com.space.haram_android.common.token.TokenManager
import com.space.haram_android.service.AuthService
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
        return authManager.saveLoginInfo(loginModel)
    }

}
