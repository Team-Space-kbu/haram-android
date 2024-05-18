package com.space.domain.auth

import com.space.data.service.auth.AuthService
import com.space.domain.NonParamUseCase
import com.space.builder_annotation.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DeleteAuthUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val authService: AuthService
) : NonParamUseCase<Boolean>(dispatcher) {
    override suspend fun execute(): Boolean {
        authService.deleteToken()
        authService.deleteLogin()
        return true
    }
}