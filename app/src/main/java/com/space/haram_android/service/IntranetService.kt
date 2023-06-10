package com.space.haram_android.service

import com.space.haram_android.common.data.response.IntranetTokenRes
import com.space.haram_android.repository.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface IntranetService {

    @GET("/v1/function/intranet/token")
    suspend fun getTokenInfo(
    ): Response<ResponseBody<IntranetTokenRes>>

}