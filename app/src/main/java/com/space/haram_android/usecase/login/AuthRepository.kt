package com.space.haram_android.usecase.login

import com.space.haram_android.common.data.model.LoginModel
import com.space.haram_android.common.data.response.LoginRes
import com.space.haram_android.usecase.ResponseBody

interface AuthRepository {
    suspend fun getSpaceAuthToken(loginModel: LoginModel): ResponseBody<LoginRes>
    fun setToken(loginRes: LoginRes)
    fun getToken(): String?
    fun saveLogin(loginModel: LoginModel)
}