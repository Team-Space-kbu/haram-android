package com.space.repository.service.partners

import com.space.shared.data.partner.Partner

interface PartnersService {
    suspend fun getPartners(): List<Partner>
}