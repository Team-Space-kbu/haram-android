package com.space.domain.usecase.singup

import com.space.data.service.signup.SignupService
import com.space.domain.UseCase
import com.space.shared.common.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SendEmailUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val signupService: SignupService
) : UseCase<String, Boolean>(dispatcher) {
    override suspend fun execute(param: String): Boolean {
        return signupService.sendEmail(param)
    }
}