package com.space.domain.usecase.partners

import com.space.domain.base.NonParamUseCase
import com.space.data.service.partners.PartnersService
import com.space.shared.common.annotation.IoDispatcher
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