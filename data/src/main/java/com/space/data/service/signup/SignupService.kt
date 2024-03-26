package com.space.data.service.signup

import com.space.shared.data.auth.UserPolicy
import com.space.shared.model.FindEmailModel
import com.space.shared.model.FindPassword
import com.space.shared.model.SignupModel

interface SignupService {
    suspend fun sendEmail(email: String): Boolean
    suspend fun verifyEmail(email: String, code: String): Boolean
    suspend fun signup(signupModel: SignupModel): Boolean
    suspend fun verifyPassword(email: String, findEmailModel: FindEmailModel): String
    suspend fun setPassword(email: String, findPassword: FindPassword): Boolean
    suspend fun policySignup(): List<UserPolicy>
}