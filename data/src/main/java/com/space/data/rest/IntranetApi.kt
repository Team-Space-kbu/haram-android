package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.home.HomeInfo
import retrofit2.http.GET

interface IntranetApi {
    @GET("/v2/intranet/student")
    suspend fun getHome(): SpaceBody<HomeInfo>
}