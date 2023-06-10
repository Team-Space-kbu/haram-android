package com.space.haram_android.service

import com.space.haram_android.common.data.model.LoginIntranetModel
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface IntranetLoginService {

    @POST("/loginApp")
    suspend fun getLogin(
        @Header("Cookie") header: String,
        @Body request: LoginIntranetModel
    ): String

    @GET("/")
    suspend fun getHome(
        @Header("Cookie") header: String
    ): String


}