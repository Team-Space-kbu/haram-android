package com.space.domain.auth

import com.space.domain.UseCase
import com.space.data.service.login.LoginService
import com.space.builder_annotation.annotation.IoDispatcher
import com.space.shared.data.auth.AuthStatus
import com.space.shared.model.LoginModel
import com.space.shared.succeeded
import com.space.shared.successOr
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginService: LoginService,
    private val authLoginModelUseCase: AuthLoginModelUseCase,
    private val authWriteUseCase: AuthWriteUseCase,
    private val tokenWriteUseCase: TokenWriteUseCase,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : UseCase<LoginModel, Boolean>(dispatcher) {
    override suspend fun execute(param: LoginModel): Boolean {
        val login = authLoginModelUseCase(param).succeeded()
        login?.let {
            val result = loginService.login(login)
            return when (result.status) {
                AuthStatus.PASS -> {
                    val auth = authWriteUseCase(param).successOr(false)
                    val token = tokenWriteUseCase(result.authToken!!).successOr(false)
                    return auth && token
                }

                else -> false
            }
        }
        return false
    }
}