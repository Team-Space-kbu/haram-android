package com.space.domain.service

import com.space.data.ResponseBody
import com.space.data.response.home.HomeInfo
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {

    @GET("/v1/homes")
    suspend fun getHome(): Response<ResponseBody<HomeInfo>>
}