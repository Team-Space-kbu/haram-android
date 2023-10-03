package com.space.domain.usecase.partners

import com.space.data.res.partners.PartnersReq
import com.space.domain.service.PartnersService
import javax.inject.Inject

class PartnersUseCase @Inject constructor(
    private val partnersService: PartnersService
){
    suspend fun getPartners(): List<PartnersReq>? {
        return partnersService.getPartnersList().body()?.data
    }
}