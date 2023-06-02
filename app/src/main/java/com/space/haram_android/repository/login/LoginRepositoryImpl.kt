package com.space.haram_android.repository.login

import com.space.haram_android.common.data.ResponseBody
import com.space.haram_android.data.response.LoginToken
import com.space.haram_android.data.model.LoginModel
import com.space.haram_android.service.LoginService
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val service: LoginService
) : LoginRepository {
    override suspend fun getSpaceAuthToken(loginModel: LoginModel): ResponseBody<LoginToken> {
        return service.getLogin(loginModel)
    }

}
