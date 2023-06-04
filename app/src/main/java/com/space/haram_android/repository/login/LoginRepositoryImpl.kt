package com.space.haram_android.repository.login

import com.space.haram_android.common.data.ResponseBody
import com.space.haram_android.data.response.LoginRes
import com.space.haram_android.data.model.login.LoginModel
import com.space.haram_android.service.LoginService
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService
) : LoginRepository {
    override suspend fun getSpaceAuthToken(loginModel: LoginModel): ResponseBody<LoginRes> {
        return loginService.getLogin(loginModel)
    }

}
