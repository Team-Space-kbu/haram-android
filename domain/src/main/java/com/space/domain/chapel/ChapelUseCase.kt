package com.space.domain.chapel

import com.space.domain.NonParamUseCase
import com.space.data.service.chpael.ChapelService
import com.space.space_annotation.annotation.IoDispatcher
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