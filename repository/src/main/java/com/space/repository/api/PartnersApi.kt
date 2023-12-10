package com.space.repository.api

import com.space.shared.SpaceBody
import com.space.shared.data.partner.Partner
import retrofit2.Response
import retrofit2.http.GET

interface PartnersApi {
    @GET("/v1/partners")
    suspend fun getPartnersList(): SpaceBody<List<Partner>>

}