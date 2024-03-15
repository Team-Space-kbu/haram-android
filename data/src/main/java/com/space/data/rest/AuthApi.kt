package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.model.LoginModel
import com.space.shared.model.AuthModel
import com.space.shared.data.auth.AuthToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

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

    @GET("/v1/mail/{userEmail}")
    suspend fun sendEmail(
        @Path("userEmail") email: String
    ): SpaceBody<Boolean>

    @GET("/v1/mail/{userEmail}/{authCode}")
    suspend fun verifyEmail(
        @Path("userEmail") email: String,
        @Path("authCode") code: String
    ): SpaceBody<Boolean>

}