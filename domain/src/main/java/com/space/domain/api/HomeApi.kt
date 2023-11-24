package com.space.domain.api

import com.space.data.ResponseBody
import com.space.data.response.home.HomeInfo
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET("/v1/homes")
    suspend fun getHome(): ResponseBody<HomeInfo>
}