package com.space.domain.usecase.singup

import com.space.data.service.signup.SignupService
import com.space.domain.UseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.model.SignupModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val signupService: SignupService
) : UseCase<SignupModel, Boolean>(dispatcher) {
    override suspend fun execute(param: SignupModel): Boolean {
        return signupService.signup(param)
    }
}