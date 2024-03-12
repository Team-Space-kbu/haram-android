package com.space.data.service.login

import com.space.shared.data.auth.Auth
import com.space.shared.model.LoginModel
import com.space.shared.model.AuthModel

interface LoginService {

    suspend fun getToken(
        refreshToken: String?,
        userId: AuthModel
    ): Auth

    suspend fun login(loginModel: LoginModel): Auth

    suspend fun logout(
        refreshToken: String?,
        authModel: AuthModel
    )
}