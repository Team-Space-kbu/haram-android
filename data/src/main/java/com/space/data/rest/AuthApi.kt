package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.model.LoginModel
import com.space.shared.model.AuthModel
import com.space.shared.data.auth.AuthToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST("/v1/auth/login")
    suspend fun getLogin(
        @Body request: LoginModel
    ): Response<SpaceBody<AuthToken>>

    @POST("/v1/auth/refresh")
    suspend fun updateAccessToken(
        @Header("Authorization") refreshToken: String?,
        @Body userId: AuthModel
    ): Response<SpaceBody<AuthToken>>

    @POST("/v1/auth/logout")
    suspend fun getLogout(
        @Header("Authorization") token: String?,
        @Body authModel: AuthModel
    ): Response<*>
}