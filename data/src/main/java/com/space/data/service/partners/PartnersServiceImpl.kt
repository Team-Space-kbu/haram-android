package com.space.data.service.partners

import com.space.data.rest.PartnersApi
import com.space.shared.data.partner.Partner
import com.space.shared.data.partner.PartnersDetail
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class PartnersServiceImpl @Inject constructor(
    private val partnersApi: PartnersApi
) : PartnersService {
    override suspend fun getPartners(): List<Partner> {
        return runBlocking {
            partnersApi.getPartnersList().data
        }
    }

    override suspend fun getPartnersDetail(id: String): PartnersDetail {
        return runBlocking {
            partnersApi.getPartnerDetail(id).data
        }
    }
}