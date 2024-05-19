package com.space.domain.rothem

import com.space.data.service.rothem.RothemService
import com.space.domain.UseCase
import com.space.space_annotation.annotation.IoDispatcher
import com.space.shared.model.ReservationsModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RothemRoomReserved @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val rothemService: RothemService
) : UseCase<Pair<String, ReservationsModel>, Boolean>(dispatcher) {
    override suspend fun execute(param: Pair<String, ReservationsModel>): Boolean {
        return rothemService.postRoomReservations(param.first, param.second)
    }
}