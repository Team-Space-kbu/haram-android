package com.space.domain.service

import com.space.data.SpaceBody
import com.space.data.response.partners.PartnersReq
import retrofit2.Response
import retrofit2.http.GET

interface PartnersService {
    @GET("/v1/partners")
    suspend fun getPartnersList(
    ): Response<SpaceBody<List<PartnersReq>>>
}