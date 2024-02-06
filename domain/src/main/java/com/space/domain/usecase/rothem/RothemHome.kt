package com.space.domain.usecase.rothem

import com.space.data.service.auth.AuthService
import com.space.data.service.rothem.RothemService
import com.space.domain.base.NonParamUseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.rothem.Rothem
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RothemHome @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val rothemService: RothemService,
    private val authService: AuthService
) : NonParamUseCase<Rothem>(dispatcher) {
    override suspend fun execute(): Rothem {
        val userId = authService.getUserId()
        return rothemService.getRothemHome(userId)
    }
}