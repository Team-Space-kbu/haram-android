package com.space.data.service.auth

import com.space.shared.data.auth.AuthToken
import com.space.shared.model.LoginModel

interface AuthService {

    fun saveLoginModel(loginModel: LoginModel): Boolean

    fun saveToken(authToken: AuthToken):Boolean

    fun getAccessToken(): String?

    fun getRefreshToken(): String?

    fun toLoginModel(loginModel: LoginModel): LoginModel
}