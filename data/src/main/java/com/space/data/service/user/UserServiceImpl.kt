package com.space.data.service.user

import com.google.gson.Gson
import com.space.data.rest.UserApi
import com.space.shared.SpaceBody
import com.space.shared.common.exception.NotFoundUserException
import com.space.shared.common.exception.signup.EmailCodeFormatException
import com.space.shared.common.exception.signup.EmailFormatException
import com.space.shared.common.exception.signup.EmailInUseException
import com.space.shared.common.exception.signup.ExpirationCodeException
import com.space.shared.common.exception.signup.FormatIncorrectException
import com.space.shared.common.exception.signup.IncorrectCodeException
import com.space.shared.common.exception.signup.NicknameFormatException
import com.space.shared.common.exception.signup.NicknameInUseException
import com.space.shared.common.exception.signup.PasswordFormatException
import com.space.shared.common.exception.signup.PasswordIncorrectCode
import com.space.shared.common.exception.signup.ToMuchRequestException
import com.space.shared.common.exception.signup.UserAlreadyExistsException
import com.space.shared.common.exception.signup.UserIdFormatException
import com.space.shared.common.exception.user.PasswordBefore
import com.space.shared.common.exception.user.PasswordDifferent
import com.space.shared.data.auth.User
import com.space.shared.model.PermutePasswordModel
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import javax.inject.Inject

internal class UserServiceImpl @Inject constructor(
    private val userApi: UserApi
) : UserService {
    private val gson = Gson()


    override suspend fun getUser(userId: String): User {
        return runBlocking {
            userApi.getUser(userId).data
        }
    }

    override suspend fun putPassword(
        userId: String,
        password: PermutePasswordModel
    ): Boolean {
        return runBlocking {
           try {
               userApi.putPassword(userId, password).data
           }catch (e: HttpException){
               emailErrorCodeHandler(e)
           }
        }
    }

    private fun emailErrorCodeHandler(e: HttpException): Boolean {
        val responseBody = e.response()?.errorBody()?.string()
        val errorObject = gson.fromJson(responseBody, SpaceBody::class.java)
        when (errorObject.code) {
            "USER08" -> throw PasswordDifferent("The password you entered is different")
            "USER09" -> throw PasswordBefore("It is the same as your existing password.")
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
            "USER01", "USER02" -> throw NotFoundUserException("User information could not be found from the server.")
            "USER19" -> throw PasswordIncorrectCode("The password-only authentication code must be [0] digits long.")
            "USER10" -> throw ExpirationCodeException("Code has expired.")
            "USER11", "USER06" -> throw PasswordIncorrectCode("The authentication code is incorrect.")
            else -> {
                throw e
            }
        }
    }
}