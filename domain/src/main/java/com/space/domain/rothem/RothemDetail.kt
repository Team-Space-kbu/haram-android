package com.space.domain.rothem

import com.space.data.service.rothem.RothemService
import com.space.domain.UseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.rothem.RoomDetail
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RothemDetail @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val rothemService: RothemService
): UseCase<String, RoomDetail>(dispatcher) {
    override suspend fun execute(param: String): RoomDetail {
        return rothemService.getRoomDetail(param)
    }
}