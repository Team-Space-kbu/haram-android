package com.space.domain.usecase.auth

import com.space.domain.base.NonParamUseCase
import com.space.security.TokenManager
import com.space.shared.common.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AuthStateUseCase @Inject constructor(
    private val tokenManager: TokenManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : NonParamUseCase<Boolean>(dispatcher) {
    override suspend fun execute(): Boolean {
        return tokenManager.getAccessToken()!!.isNotEmpty()
    }

}