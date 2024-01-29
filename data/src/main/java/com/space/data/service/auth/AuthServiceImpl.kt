package com.space.data.service.auth

import com.space.security.AuthManager
import com.space.security.TokenManager
import com.space.security.di.DeviceSecure
import com.space.shared.data.auth.AuthToken
import com.space.shared.model.LoginModel
import com.space.shared.model.RefreshModel
import javax.inject.Inject

internal class AuthServiceImpl @Inject constructor(
    private val tokenManager: TokenManager,
    private val authManager: AuthManager,
    private val deviceSecure: DeviceSecure
) : AuthService {
    override fun saveLoginModel(loginModel: LoginModel):Boolean {
        return authManager.saveLoginModel(loginModel)
    }

    override fun saveToken(authToken: AuthToken): Boolean {
        return tokenManager.setToken(authToken)
    }

    override fun getLoginModel(): LoginModel {
        return authManager.getLoginModel()
    }

    override fun getAccessToken(): String? {
        return tokenManager.getAccessToken()
    }

    override fun getRefreshToken(): String? {
        return tokenManager.getRefreshToken()
    }

    override fun getRefreshModel(): RefreshModel {
        return authManager.getRefreshModel()
    }

    override fun toLoginModel(loginModel: LoginModel): LoginModel {
        return LoginModel(
            loginModel.userId,
            loginModel.userPassword,
            deviceSecure.ssid
        )
    }

    override fun deleteToken() {
        return tokenManager.deleteToken()
    }

    override fun deleteLogin() {
        return authManager.deleteLogin()
    }

}