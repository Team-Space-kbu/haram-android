package com.space.domain.auth

import com.space.data.service.auth.AuthService
import com.space.data.service.login.LoginService
import com.space.domain.NonParamUseCase
import com.space.space_annotation.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val authService: AuthService,
    private val loginService: LoginService
) : NonParamUseCase<Boolean>(dispatcher) {
    override suspend fun execute(): Boolean {
        val model = authService.getAuthModel()
        val token = authService.getAccessToken()
        try {
            loginService.logout(token, model)
            authService.deleteLogin()
            authService.deleteToken()
            return true
        } catch (e: Exception) {
            throw e
        }
    }
}