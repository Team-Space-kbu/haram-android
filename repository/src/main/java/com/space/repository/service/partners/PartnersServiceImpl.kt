package com.space.repository.service.partners

import com.space.repository.api.PartnersApi
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