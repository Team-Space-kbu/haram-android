package com.space.domain.service

import com.space.data.ResponseBody
import com.space.data.model.LoginModel
import com.space.data.model.RefreshModel
import com.space.data.res.LoginRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("/v1/auth/login")
    suspend fun getLogin(
        @Body request: LoginModel
    ): Response<ResponseBody<LoginRes>>

    @POST("/v1/auth/refresh")
    suspend fun updateAccessToken(
        @Header("Authorization") refreshToken: String?,
        @Body userId: RefreshModel
    ): Response<ResponseBody<LoginRes>>


}