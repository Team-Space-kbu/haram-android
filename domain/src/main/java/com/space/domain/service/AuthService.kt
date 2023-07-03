package com.space.domain.service

import com.space.data.ResponseBody
import com.space.data.model.LoginModel
import com.space.data.response.LoginRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("/v1/login")
    suspend fun getLogin(
        @Body request: LoginModel
    ): ResponseBody<LoginRes>

    @GET("/v1/refresh")
    suspend fun updateAccessToken(
        @Header("Authorization") refreshToken: String?
    ): Response<ResponseBody<LoginRes>>


}