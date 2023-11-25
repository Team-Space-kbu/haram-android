package com.space.domain.api

import com.space.data.SpaceBody
import com.space.data.data.home.HomeInfo
import retrofit2.http.GET

interface HomeApi {

    @GET("/v1/homes")
    suspend fun getHome(): SpaceBody<HomeInfo>
}