package com.space.domain.usecase.auth

import com.space.data.service.auth.AuthService
import com.space.domain.base.NonParamUseCase
import com.space.shared.common.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AuthStateUseCase @Inject constructor(
    private val authService: AuthService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : NonParamUseCase<Boolean>(dispatcher) {
    override suspend fun execute(): Boolean {
        return authService.getAccessToken()!!.isNotEmpty()
    }

}