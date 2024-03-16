package com.space.domain.usecase.singup

import com.space.data.service.signup.SignupService
import com.space.domain.UseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.model.FindEmailModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FindPwVerifyUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val signupService: SignupService
) : UseCase<Pair<String, FindEmailModel>, String>(dispatcher) {
    override suspend fun execute(param: Pair<String, FindEmailModel>): String {
        return signupService.verifyPassword(param.first, param.second)
    }
}