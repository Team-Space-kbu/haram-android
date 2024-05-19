package com.space.domain.partners

import com.space.domain.NonParamUseCase
import com.space.data.service.partners.PartnersService
import com.space.space_annotation.annotation.IoDispatcher
import com.space.shared.data.partner.Partner
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class PartnersUseCase @Inject constructor(
    private val partnersService: PartnersService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : NonParamUseCase<List<Partner>>(dispatcher) {
    override suspend fun execute(): List<Partner> {
        return partnersService.getPartners()
    }
}