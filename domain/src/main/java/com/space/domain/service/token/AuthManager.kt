package com.space.domain.service.token

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.space.data.model.LoginModel
import com.space.shared.annotation.AuthEncrypted
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