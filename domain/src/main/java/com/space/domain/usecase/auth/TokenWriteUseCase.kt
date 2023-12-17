package com.space.domain.usecase.auth

import com.space.domain.base.UseCase
import com.space.repository.di.token.TokenManager
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.auth.AuthToken
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class TokenWriteUseCase @Inject constructor(
    private val tokenManager: TokenManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
):UseCase<AuthToken, Boolean>(dispatcher) {
    override suspend fun execute(param: AuthToken): Boolean {
        return tokenManager.setToken(param)
    }
}