package com.space.domain.service

import com.space.shared.model.LoginIntranetModel
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