package com.space.domain2.usecase

import com.space.data.ResponseBody
import com.space.data.model.LoginModel
import com.space.data.response.LoginRes
import com.space.domain2.api.SpaceAuthApi
import com.space.repository.token.AuthManager
import com.space.repository.token.TokenManager
import javax.inject.Inject


class AuthUseCase @Inject constructor(
    private val authService: SpaceAuthApi,
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
