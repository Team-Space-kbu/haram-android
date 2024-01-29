package com.space.data.service.auth

import com.space.shared.data.auth.AuthToken
import com.space.shared.model.LoginModel
import com.space.shared.model.RefreshModel

interface AuthService {
    fun saveLoginModel(loginModel: LoginModel): Boolean
    fun saveToken(authToken: AuthToken):Boolean
    fun getLoginModel(): LoginModel
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun getRefreshModel(): RefreshModel
    fun toLoginModel(loginModel: LoginModel): LoginModel
    fun deleteToken()
    fun deleteLogin()
}