package com.space.domain2.api

import com.space.data.ResponseBody
import com.space.data.response.home.HomeInfo
import retrofit2.http.GET

interface SpaceHomeApi {

    @GET("/v1/homes")
    suspend fun getHome(): ResponseBody<HomeInfo>
}