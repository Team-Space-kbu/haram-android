package com.space.haram_android.repository.login

import com.space.haram_android.repository.ResponseBody
import com.space.haram_android.common.data.model.LoginModel
import com.space.haram_android.common.data.response.LoginRes

interface AuthRepository {
    suspend fun getSpaceAuthToken(loginModel: LoginModel): ResponseBody<LoginRes>
    fun setToken(loginRes: LoginRes)
    fun getToken(): String?
    fun saveLogin(loginModel: LoginModel)
}