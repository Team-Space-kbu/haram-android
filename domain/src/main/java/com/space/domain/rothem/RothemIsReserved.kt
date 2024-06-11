package com.space.domain.rothem

import com.space.data.service.auth.AuthService
import com.space.data.service.rothem.RothemService
import com.space.domain.NonParamUseCase
import com.space.shared.annotation.IoDispatcher
import com.space.shared.data.rothem.Reservation
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RothemIsReserved @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val rothemService: RothemService,
    private val authService: AuthService
) : NonParamUseCase<Reservation>(dispatcher) {
    override suspend fun execute(): Reservation {
        val userId = authService.getUserId()
        return rothemService.getReservations(userId)
    }
}