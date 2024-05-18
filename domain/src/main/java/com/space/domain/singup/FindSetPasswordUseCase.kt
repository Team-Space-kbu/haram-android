package com.space.domain.singup

import com.space.data.service.signup.SignupService
import com.space.domain.UseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.model.FindPassword
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FindSetPasswordUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val signupService: SignupService
) : UseCase<Pair<String, FindPassword>, Boolean>(dispatcher) {
    override suspend fun execute(param: Pair<String, FindPassword>): Boolean {
        return signupService.setPassword(param.first, param.second)
    }
}