package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.partner.Partner
import com.space.shared.data.partner.PartnersDetail
import retrofit2.http.GET
import retrofit2.http.Path

interface PartnersApi {
    @GET("/v1/partners")
    suspend fun getPartnersList(): SpaceBody<List<Partner>>

    @GET("/v1/partners/{id}")
    suspend fun getPartnerDetail(
        @Path("id") id: String
    ): SpaceBody<PartnersDetail>
}