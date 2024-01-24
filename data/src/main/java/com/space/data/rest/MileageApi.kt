package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.mileage.MileageInfo
import retrofit2.http.GET

interface MileageApi {

    @GET("/v1/mileage")
    suspend fun getMileage(): SpaceBody<MileageInfo>
}