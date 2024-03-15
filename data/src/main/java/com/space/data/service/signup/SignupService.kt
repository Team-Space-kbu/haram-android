package com.space.data.service.signup

interface SignupService {
    suspend fun sendEmail(email: String): Boolean
    suspend fun verifyEmail(email: String, code: String): Boolean
}