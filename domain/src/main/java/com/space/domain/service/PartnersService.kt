package com.space.domain.service

import SpaceBody
import data.partner.Partner
import retrofit2.Response
import retrofit2.http.GET

interface PartnersService {
    @GET("/v1/partners")
    suspend fun getPartnersList(
    ): Response<SpaceBody<List<Partner>>>
}