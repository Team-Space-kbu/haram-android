package com.space.domain.service

import com.space.shared.SpaceBody
import com.space.shared.response.intranet.ChapelInfoReq
import com.space.shared.response.intranet.ChapelListRes
import com.space.shared.response.intranet.IntranetTokenRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IntranetService {

    @GET("/v1/function/intranet/token")
    suspend fun getTokenInfo(
    ): Response<SpaceBody<IntranetTokenRes>>

    @POST("/v1/function/chapel/info")
    suspend fun getChapelInfo(
        @Body tokenRes: IntranetTokenRes
    ): Response<SpaceBody<ChapelInfoReq>>

    @POST("/v1/function/chapel/list")
    suspend fun getChapelList(
        @Body tokenRes: IntranetTokenRes
    ): Response<SpaceBody<List<ChapelListRes>>>

}