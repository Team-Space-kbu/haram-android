package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.model.IntranetModel
import retrofit2.http.Body
import retrofit2.http.POST

interface IntranetApi {
    @POST("/v2/intranet/student")
    suspend fun postIntranet(
        @Body intranet: IntranetModel
    ): SpaceBody<String>
}