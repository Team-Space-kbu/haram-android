package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.home.HomeInfo
import retrofit2.http.GET

interface HomeApi {

    @GET("/v2/homes")
    suspend fun getHome(): SpaceBody<HomeInfo>

}