package com.space.domain.usecase.chapel

import com.space.domain.NonParamUseCase
import com.space.data.service.chpael.ChapelService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.chapel.Chapel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ChapelUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val chapelService: ChapelService,
) : NonParamUseCase<Chapel>(dispatcher) {
    override suspend fun execute(): Chapel {
        val chapelDay = chapelService.getChapelInfo()
        val chapelDetail = chapelService.getChapelDetail()
        return Chapel(chapelDay, chapelDetail)
    }
}