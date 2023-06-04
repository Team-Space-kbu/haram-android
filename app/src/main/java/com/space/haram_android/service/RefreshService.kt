package com.space.haram_android.service

import com.space.haram_android.common.data.ResponseBody
import com.space.haram_android.data.response.LoginRes
import retrofit2.http.GET
import retrofit2.http.Header

interface RefreshService {

    @GET("/v1/refresh")
    suspend fun updateAccessToken(
        @Header("refreshToken") refreshToken: String?
    ): ResponseBody<LoginRes>
}