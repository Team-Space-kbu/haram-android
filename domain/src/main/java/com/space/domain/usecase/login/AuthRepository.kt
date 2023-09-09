package com.space.domain.usecase.login

import com.space.data.ResponseBody
import com.space.data.model.LoginModel
import com.space.data.res.LoginRes

interface AuthRepository {
    suspend fun getSpaceAuthToken(loginModel: LoginModel): ResponseBody<LoginRes>
    fun setToken(loginRes: LoginRes)
    fun getToken(): String?
    fun saveLogin(loginModel: LoginModel)
}