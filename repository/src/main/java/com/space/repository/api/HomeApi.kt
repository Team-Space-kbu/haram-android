package com.space.repository.api

import SpaceBody
import data.home.HomeInfo
import data.home.Shortcut
import retrofit2.http.GET

interface HomeApi {

    @GET("/v1/homes")
    suspend fun getHome(): SpaceBody<HomeInfo>

    @GET("/space/repository/src/main/assets/shortcut.json")
    suspend fun getShortcut(): List<Shortcut>
}