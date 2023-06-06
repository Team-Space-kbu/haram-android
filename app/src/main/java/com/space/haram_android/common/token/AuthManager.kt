package com.space.haram_android.common.token

import android.content.SharedPreferences
import com.space.haram_android.common.data.model.LoginModel
import com.space.haram_android.di.encrypted.AuthSharedPreferencesModule.AuthEncrypted
import javax.inject.Inject

class AuthManager @Inject constructor(
    @AuthEncrypted private val sharedPreferences: SharedPreferences
) {

    fun getLoginInfo(): LoginModel {
        return LoginModel(
            sharedPreferences.getString("userId", null),
            sharedPreferences.getString("userPw", null)
        )
    }

    fun saveLoginInfo(loginModel: LoginModel) {
        loginModel.let {
            sharedPreferences.edit().putString("userId", it.userId).apply()
            sharedPreferences.edit().putString("userPw", it.password).apply()
        }
    }

    fun deleteLoginInfo() {
        sharedPreferences.edit().clear().apply()
    }


}