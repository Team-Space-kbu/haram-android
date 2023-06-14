package com.space.haram_android.service

import com.space.haram_android.common.data.response.intranet.ChapelInfoReq
import com.space.haram_android.common.data.response.intranet.ChapelListRes
import com.space.haram_android.common.data.response.intranet.IntranetTokenRes
import com.space.haram_android.repository.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IntranetService {

    @GET("/v1/function/intranet/token")
    suspend fun getTokenInfo(
    ): Response<ResponseBody<IntranetTokenRes>>

    @POST("/v1/function/chapel/info")
    suspend fun getChapelInfo(
        @Body tokenRes: IntranetTokenRes
    ): Response<ResponseBody<ChapelInfoReq>>

    @POST("/v1/function/chapel/list")
    suspend fun getChapelList(
        @Body tokenRes: IntranetTokenRes
    ): Response<ResponseBody<List<ChapelListRes>>>

}