package com.space.haram_android.service

import com.space.haram_android.data.HomeRes
import com.space.haram_android.data.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface HomeService {

    @GET("/home")
    fun getHome() : Call<ResponseBody<HomeRes>>

}