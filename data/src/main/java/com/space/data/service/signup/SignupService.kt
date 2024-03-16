package com.space.data.service.signup

import com.space.shared.model.SignupModel

interface SignupService {
    suspend fun sendEmail(email: String): Boolean
    suspend fun verifyEmail(email: String, code: String): Boolean
    suspend fun signup(signupModel: SignupModel):Boolean
}