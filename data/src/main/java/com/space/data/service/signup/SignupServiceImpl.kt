package com.space.data.service.signup

import com.google.gson.Gson
import com.space.data.rest.AuthApi
import com.space.shared.SpaceBody
import com.space.shared.common.exception.signup.EmailCodeFormatException
import com.space.shared.common.exception.signup.EmailFormatException
import com.space.shared.common.exception.signup.EmailInUseException
import com.space.shared.common.exception.signup.ExpirationCodeException
import com.space.shared.common.exception.signup.ExpiredVerificationCodeException
import com.space.shared.common.exception.signup.FormatIncorrectException
import com.space.shared.common.exception.signup.IncorrectCodeException
import com.space.shared.common.exception.signup.InvalidVerificationCodeException
import com.space.shared.common.exception.signup.NicknameFormatException
import com.space.shared.common.exception.signup.NicknameInUseException
import com.space.shared.common.exception.signup.PasswordFormatException
import com.space.shared.common.exception.signup.ToMuchRequestException
import com.space.shared.common.exception.signup.UserAlreadyExistsException
import com.space.shared.common.exception.signup.UserIdFormatException
import com.space.shared.model.SignupModel
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
                emailErrorCodeHandler(e)

            }
        }
    }

    override suspend fun verifyEmail(email: String, code: String): Boolean {
        return runBlocking {
            try {
                authApi.verifyEmail(email, code).data
            } catch (e: HttpException) {
                emailErrorCodeHandler(e)
            }
        }
    }

    override suspend fun signup(signupModel: SignupModel): Boolean {
        return runBlocking {
            try {
                authApi.signup(signupModel).data
            } catch (e: HttpException) {
                emailErrorCodeHandler(e)
            }
        }
    }

    private fun emailErrorCodeHandler(e: HttpException): Boolean {
        val responseBody = e.response()?.errorBody()?.string()
        val errorObject = gson.fromJson(responseBody, SpaceBody::class.java)
        when (errorObject.code) {
            "MAIL01" -> throw IncorrectCodeException("Incorrect code!!")
            "MAIL02" -> throw ExpirationCodeException("Code has expired.")
            "MAIL04" -> throw FormatIncorrectException("The format does not match. @bible.ac.kr")
            "MAIL05" -> throw ToMuchRequestException("Too many email requests")
            "USER14" -> throw UserIdFormatException("User ID format is incorrect. It should be alphanumeric and between 4 to 30 characters.")
            "USER15" -> throw EmailFormatException("Email format is incorrect. It should be in the format of @bible.ac.kr and between 2 to 255 characters.")
            "USER16" -> throw PasswordFormatException("Password format is incorrect. It should be between 8 to 255 characters.")
            "USER17" -> throw NicknameFormatException("Nickname format is incorrect. It should be alphanumeric and between 2 to 15 characters.")
            "USER18" -> throw EmailCodeFormatException("Email code should be exactly 6 characters long.")
            "USER04" -> throw UserAlreadyExistsException("User already exists.")
            "USER13" -> throw EmailInUseException("Email is already in use.")
            "USER07" -> throw NicknameInUseException("Nickname is already in use.")

            else -> {
                throw e
            }
        }
    }
}