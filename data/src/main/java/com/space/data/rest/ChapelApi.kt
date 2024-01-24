package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.chapel.ChapelInfo
import com.space.shared.data.chapel.ChapelDetail
import retrofit2.http.GET

interface ChapelApi {

    @GET("/v2/intranet/chapel/info")
    suspend fun getChapelInfo(
    ): SpaceBody<ChapelInfo>

    @GET("/v2/intranet/chapel/detail")
    suspend fun getChapelDetail(
    ): SpaceBody<List<ChapelDetail>>

}