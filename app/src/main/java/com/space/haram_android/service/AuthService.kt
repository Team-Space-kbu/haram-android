package com.space.haram_android.service

import com.space.haram_android.repository.ResponseBody
import com.space.haram_android.common.data.response.LoginRes
import com.space.haram_android.common.data.model.login.LoginModel
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
        @Header("refreshToken") refreshToken: String?
    ): Response<ResponseBody<LoginRes>>


}