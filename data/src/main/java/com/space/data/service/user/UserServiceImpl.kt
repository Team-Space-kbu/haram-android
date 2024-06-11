package com.space.data.service.user

import com.google.gson.Gson
import com.space.data.rest.UserApi
import com.space.shared.SpaceBody
import com.space.shared.exception.NotFoundUserException
import com.space.shared.exception.signup.EmailCodeFormatException
import com.space.shared.exception.signup.EmailFormatException
import com.space.shared.exception.signup.EmailInUseException
import com.space.shared.exception.signup.ExpirationCodeException
import com.space.shared.exception.signup.FormatIncorrectException
import com.space.shared.exception.signup.IncorrectCodeException
import com.space.shared.exception.signup.NicknameFormatException
import com.space.shared.exception.signup.NicknameInUseException
import com.space.shared.exception.signup.PasswordFormatException
import com.space.shared.exception.signup.PasswordIncorrectCode
import com.space.shared.exception.signup.ToMuchRequestException
import com.space.shared.exception.signup.UserAlreadyExistsException
import com.space.shared.exception.signup.UserIdFormatException
import com.space.shared.exception.user.PasswordBefore
import com.space.shared.exception.user.PasswordDifferent
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
            "USER08" -> throw com.space.shared.exception.user.PasswordDifferent("The password you entered is different")
            "USER09" -> throw com.space.shared.exception.user.PasswordBefore("It is the same as your existing password.")
            "MAIL01" -> throw com.space.shared.exception.signup.IncorrectCodeException("Incorrect code!!")
            "MAIL02" -> throw com.space.shared.exception.signup.ExpirationCodeException("Code has expired.")
            "MAIL04" -> throw com.space.shared.exception.signup.FormatIncorrectException("The format does not match. @bible.ac.kr")
            "MAIL05" -> throw com.space.shared.exception.signup.ToMuchRequestException("Too many email requests")
            "USER14" -> throw com.space.shared.exception.signup.UserIdFormatException("User ID format is incorrect. It should be alphanumeric and between 4 to 30 characters.")
            "USER15" -> throw com.space.shared.exception.signup.EmailFormatException("Email format is incorrect. It should be in the format of @bible.ac.kr and between 2 to 255 characters.")
            "USER16" -> throw com.space.shared.exception.signup.PasswordFormatException("Password format is incorrect. It should be between 8 to 255 characters.")
            "USER17" -> throw com.space.shared.exception.signup.NicknameFormatException("Nickname format is incorrect. It should be alphanumeric and between 2 to 15 characters.")
            "USER18" -> throw com.space.shared.exception.signup.EmailCodeFormatException("Email code should be exactly 6 characters long.")
            "USER04" -> throw com.space.shared.exception.signup.UserAlreadyExistsException("User already exists.")
            "USER13" -> throw com.space.shared.exception.signup.EmailInUseException("Email is already in use.")
            "USER07" -> throw com.space.shared.exception.signup.NicknameInUseException("Nickname is already in use.")
            "USER01", "USER02" -> throw com.space.shared.exception.NotFoundUserException("User information could not be found from the server.")
            "USER19" -> throw com.space.shared.exception.signup.PasswordIncorrectCode("The password-only authentication code must be [0] digits long.")
            "USER10" -> throw com.space.shared.exception.signup.ExpirationCodeException("Code has expired.")
            "USER11", "USER06" -> throw com.space.shared.exception.signup.PasswordIncorrectCode("The authentication code is incorrect.")
            else -> {
                throw e
            }
        }
    }
}