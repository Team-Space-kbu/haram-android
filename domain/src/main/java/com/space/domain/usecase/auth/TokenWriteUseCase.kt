package com.space.domain.usecase.auth

import com.space.data.service.auth.AuthService
import com.space.domain.base.UseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.auth.AuthToken
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class TokenWriteUseCase @Inject constructor(
    private val authService: AuthService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
):UseCase<AuthToken, Boolean>(dispatcher) {
    override suspend fun execute(param: AuthToken): Boolean {
        return authService.saveToken(param)
    }
}