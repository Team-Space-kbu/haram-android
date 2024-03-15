package com.space.data.service.signup

import com.google.gson.Gson
import com.space.data.rest.AuthApi
import com.space.shared.SpaceBody
import com.space.shared.common.exception.signup.ExpirationCode
import com.space.shared.common.exception.signup.FormatIncorrect
import com.space.shared.common.exception.signup.IncorrectCode
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import javax.inject.Inject

internal class SignupServiceImpl @Inject constructor(
    private val authApi: AuthApi
) : SignupService {

    private val gson = Gson()
    override suspend fun sendEmail(email: String): Boolean {
        return runBlocking {
            try {
                authApi.sendEmail(email).data
            } catch (e: HttpException) {
                errorCodeHandler(e)

            }
        }
    }

    override suspend fun verifyEmail(email: String, code: String): Boolean {
        return runBlocking {
            try {
                authApi.verifyEmail(email, code).data
            } catch (e: HttpException) {
                errorCodeHandler(e)
            }
        }
    }

    private fun errorCodeHandler(e: HttpException): Boolean {
        val responseBody = e.response()?.errorBody()?.string()
        val errorObject = gson.fromJson(responseBody, SpaceBody::class.java)
        when (errorObject.code) {
            "MAIL04" -> {
                throw FormatIncorrect("The format does not match. @bible.ac.kr")
            }

            "MAIL02" -> {
                throw ExpirationCode("Code has expired.")
            }

            "MAIL01" -> {
                throw IncorrectCode("Incorrect code!!")
            }

            else -> {
                throw e
            }
        }
    }
}