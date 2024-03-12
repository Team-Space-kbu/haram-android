package com.space.data.service.auth

import com.space.shared.data.auth.AuthToken
import com.space.shared.model.LoginModel
import com.space.shared.model.AuthModel

interface AuthService {
    fun getUserId(): String
    fun saveLoginModel(loginModel: LoginModel): Boolean
    fun saveToken(authToken: AuthToken): Boolean
    fun getLoginModel(): LoginModel
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun getAuthModel(): AuthModel
    fun toLoginModel(loginModel: LoginModel): LoginModel
    fun deleteToken()
    fun deleteLogin()
}