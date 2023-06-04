package com.space.haram_android.service

import com.space.haram_android.common.data.ResponseBody
import com.space.haram_android.data.response.LoginRes
import com.space.haram_android.data.model.login.LoginModel
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/v1/login")
    suspend fun getLogin(
        @Body request: LoginModel
    ): ResponseBody<LoginRes>
}