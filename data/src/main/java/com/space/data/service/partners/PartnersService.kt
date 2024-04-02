package com.space.data.service.partners

import com.space.shared.data.partner.Partner
import com.space.shared.data.partner.PartnersDetail

interface PartnersService {
    suspend fun getPartners(): List<Partner>

    suspend fun getPartnersDetail(id:String): PartnersDetail
}