package com.space.haram_android.common.token

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.space.haram_android.common.annotation.AuthEncrypted
import com.space.haram_android.common.data.model.LoginModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AuthManager @Inject constructor(
    @AuthEncrypted private val sharedPreferences: SharedPreferences
) {

    fun getLoginModel(): LoginModel {
        return LoginModel(
            sharedPreferences.getString("userId", null),
            sharedPreferences.getString("userPw", null)
        )
    }


    fun saveLoginModel(loginModel: LoginModel) {
        loginModel.let {
            sharedPreferences.edit().putString("userId", it.userId).apply()
            sharedPreferences.edit().putString("userPw", it.password).apply()
        }
    }


    @SuppressLint("ApplySharedPref")
    fun deleteLogin() {
        runBlocking {
            sharedPreferences.edit().remove("userId").commit()
            sharedPreferences.edit().remove("userPw").commit()
        }
    }


}