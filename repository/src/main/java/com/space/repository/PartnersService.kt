package com.space.repository

import com.space.shared.data.partner.Partner

interface PartnersService {
    suspend fun getPartners(): List<Partner>
}