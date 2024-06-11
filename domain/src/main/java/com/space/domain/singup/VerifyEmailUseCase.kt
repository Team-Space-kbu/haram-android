package com.space.domain.singup

import com.space.data.service.signup.SignupService
import com.space.domain.UseCase
import com.space.shared.annotation.IoDispatcher
import com.space.shared.model.EmailModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class VerifyEmailUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val signupService: SignupService
) : UseCase<EmailModel, Boolean>(dispatcher) {
    override suspend fun execute(param: EmailModel): Boolean {
        return signupService.verifyEmail(param.getEmailModel(), param.code)
    }
}