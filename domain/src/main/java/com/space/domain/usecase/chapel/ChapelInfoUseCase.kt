package com.space.domain.usecase.chapel

import com.space.domain.NonParamUseCase
import com.space.data.service.chpael.ChapelService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.chapel.ChapelInfo
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ChapelInfoUseCase @Inject constructor(
    private val chapelService: ChapelService,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : NonParamUseCase<ChapelInfo>(dispatcher) {
    override suspend fun execute(): ChapelInfo {
        return chapelService.getChapelInfo()
    }

}