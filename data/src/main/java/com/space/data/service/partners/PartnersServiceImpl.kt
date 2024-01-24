package com.space.data.service.partners

import com.space.data.rest.PartnersApi
import com.space.shared.data.partner.Partner
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
}