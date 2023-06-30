package com.space.haram_android.service

import com.space.haram_android.usecase.ResponseBody
import com.space.haram_android.common.data.response.home.HomeRes
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {

    @GET("/v1/home")
    suspend fun getHome(): Response<ResponseBody<HomeRes>>
}