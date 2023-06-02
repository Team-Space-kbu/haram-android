package com.space.haram_android.repository.login

import com.space.haram_android.common.data.ResponseBody
import com.space.haram_android.data.model.LoginModel
import com.space.haram_android.data.response.LoginToken

interface LoginRepository {
    suspend fun getSpaceAuthToken(loginModel: LoginModel): ResponseBody<LoginToken>
}