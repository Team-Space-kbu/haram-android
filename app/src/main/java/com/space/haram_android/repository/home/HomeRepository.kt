package com.space.haram_android.repository.home

import com.space.haram_android.repository.ResponseBody
import com.space.haram_android.common.data.response.HomeRes
import retrofit2.Response


interface HomeRepository {

    suspend fun getHome(): Response<ResponseBody<HomeRes>>
}