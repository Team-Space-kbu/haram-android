package com.space.domain.partners

import com.space.data.service.partners.PartnersService
import com.space.domain.UseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.partner.PartnersDetail
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class PartnersDetailUseCase @Inject constructor(
    private val partnersService: PartnersService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : UseCase<String, PartnersDetail>(dispatcher) {
    override suspend fun execute(param: String): PartnersDetail {
        return partnersService.getPartnersDetail(param)
    }
}