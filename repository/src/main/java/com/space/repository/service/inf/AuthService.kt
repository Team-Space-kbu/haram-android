package com.space.repository.service.inf

import com.space.shared.SpaceBody
import com.space.shared.data.auth.AuthToken
import com.space.shared.model.LoginModel
import retrofit2.Response

interface AuthService {

    suspend fun getToken(
        refreshToken: String?,
        userId: String?
    ): Response<SpaceBody<AuthToken>>

    suspend fun login(loginModel: LoginModel) : Response<SpaceBody<AuthToken>>
}