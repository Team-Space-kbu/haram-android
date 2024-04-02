package com.space.domain.auth

import com.space.data.service.auth.AuthService
import com.space.domain.UseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.model.LoginModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AuthLoginModelUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val authService: AuthService
) : UseCase<LoginModel, LoginModel>(dispatcher) {
    override suspend fun execute(param: LoginModel): LoginModel {
        return authService.toLoginModel(param)
    }
}