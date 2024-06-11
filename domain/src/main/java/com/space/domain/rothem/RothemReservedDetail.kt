package com.space.domain.rothem

import com.space.data.service.rothem.RothemService
import com.space.domain.UseCase
import com.space.space_annotation.annotation.IoDispatcher
import com.space.shared.data.rothem.RoomReservation
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RothemReservedDetail @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val rothemService: RothemService
) : UseCase<String, RoomReservation>(dispatcher) {
    override suspend fun execute(param: String): RoomReservation {
        return rothemService.getRoomReservations(param)
    }
}