package com.space.data.service.auth

import com.space.shared.data.auth.Auth
import com.space.shared.model.LoginModel
import com.space.shared.model.RefreshModel

interface AuthService {

    suspend fun getToken(
        refreshToken: String?,
        userId: RefreshModel
    ): Auth

    suspend fun login(loginModel: LoginModel) : Auth
}