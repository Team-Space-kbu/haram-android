package com.space.domain.singup

import com.space.data.service.signup.SignupService
import com.space.domain.NonParamUseCase
import com.space.builder_annotation.annotation.IoDispatcher
import com.space.shared.data.auth.UserPolicy
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class PolicySignupUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val signupService: SignupService
) : NonParamUseCase<List<UserPolicy>>(dispatcher) {
    override suspend fun execute(): List<UserPolicy> {
        return signupService.policySignup()
    }
}