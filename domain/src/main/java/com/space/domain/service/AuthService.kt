package com.space.domain.service

import SpaceBody
import model.LoginModel
import model.RefreshModel
import data.auth.AuthToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("/v1/auth/login")
    suspend fun getLogin(
        @Body request: LoginModel
    ): Response<SpaceBody<AuthToken>>

    @POST("/v1/auth/refresh")
    suspend fun updateAccessToken(
        @Header("Authorization") refreshToken: String?,
        @Body userId: RefreshModel
    ): Response<SpaceBody<AuthToken>>


}