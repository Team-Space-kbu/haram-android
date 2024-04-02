package com.space.domain.rothem

import com.space.data.service.auth.AuthService
import com.space.data.service.rothem.RothemService
import com.space.domain.UseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.model.DeleteReservations
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RothemIsDeleteReserved @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val rothemService: RothemService,
    private val authService: AuthService
) : UseCase<Int, Boolean>(dispatcher) {
    override suspend fun execute(param: Int): Boolean {
        val userId = authService.getUserId()
        val model = DeleteReservations(param, userId)
        return rothemService.deleteReservations(model)
    }
}