package com.space.domain.usecase.auth

import com.space.domain.base.UseCase
import com.space.repository.service.inf.AuthService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.auth.AuthStatus
import com.space.shared.model.LoginModel
import com.space.shared.result.successOr
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authService: AuthService,
    private val authWriteUseCase: AuthWriteUseCase,
    private val tokenWriteUseCase: TokenWriteUseCase,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : UseCase<LoginModel, Boolean>(dispatcher) {
    override suspend fun execute(param: LoginModel): Boolean {
        val result = authService.login(param)
        return when (result.status) {
            AuthStatus.PASS -> {
                val auth = authWriteUseCase(param).successOr(false)
                val token = tokenWriteUseCase(result.authToken!!).successOr(false)
                return auth && token
            }

            else -> false

        }
    }
}