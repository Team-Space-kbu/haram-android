package com.space.domain.usecase

import com.space.data.response.partners.PartnersReq
import com.space.domain.service.PartnersService
import javax.inject.Inject

class PartnersUseCase @Inject constructor(
    private val partnersService: PartnersService
){
    suspend fun getPartners(): List<PartnersReq>? {
        return partnersService.getPartnersList().body()?.data
    }
}