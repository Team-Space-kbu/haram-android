package com.space.domain.chapel

import com.space.domain.NonParamUseCase
import com.space.data.service.chpael.ChapelService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.chapel.ChapelDetail
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ChapelDetailUseCase @Inject constructor(
    private val chapelService: ChapelService,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : NonParamUseCase<List<ChapelDetail>>(dispatcher) {
    override suspend fun execute(): List<ChapelDetail> {
        return chapelService.getChapelDetail()
    }

}