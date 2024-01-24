package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.home.HomeInfo
import com.space.shared.data.home.Shortcut
import retrofit2.http.GET

interface HomeApi {

    @GET("/v1/homes")
    suspend fun getHome(): SpaceBody<HomeInfo>

    @GET("/space/repository/src/main/assets/shortcut.json")
    suspend fun getShortcut(): List<Shortcut>
}