package com.space.domain.rothem

import com.space.data.service.auth.AuthService
import com.space.data.service.rothem.RothemService
import com.space.domain.NonParamUseCase
import com.space.shared.annotation.IoDispatcher
import com.space.shared.data.rothem.RothemHome
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RothemHomeUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val rothemService: RothemService,
    private val authService: AuthService
) : NonParamUseCase<RothemHome>(dispatcher) {
    override suspend fun execute(): RothemHome {
        val userId = authService.getUserId()
        return rothemService.getRothemHome(userId)
    }
}