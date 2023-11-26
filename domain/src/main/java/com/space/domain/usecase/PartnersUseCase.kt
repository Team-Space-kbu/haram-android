package com.space.domain.usecase

import data.partner.Partner
import com.space.domain.service.PartnersService
import javax.inject.Inject

class PartnersUseCase @Inject constructor(
    private val partnersService: PartnersService
){
    suspend fun getPartners(): List<Partner>? {
        return partnersService.getPartnersList().body()?.data
    }
}