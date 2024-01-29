package com.space.domain.usecase.auth

import com.space.data.service.auth.AuthService
import com.space.domain.base.UseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.model.LoginModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AuthWriteUseCase @Inject constructor(
    private val authService: AuthService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : UseCase<LoginModel, Boolean>(dispatcher) {
    override suspend fun execute(param: LoginModel): Boolean {
        return authService.saveLoginModel(param)
    }
}