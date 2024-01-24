package com.space.security

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.space.shared.model.LoginModel
import com.space.shared.common.annotation.AuthEncrypted
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AuthManager @Inject constructor(
    @AuthEncrypted private val sharedPreferences: SharedPreferences
) {

    fun getUserId(): String? = sharedPreferences.getString("userId", null)

    fun getLoginModel(): LoginModel {
        return LoginModel(
            sharedPreferences.getString("userId", null),
            sharedPreferences.getString("userPw", null)
        )
    }


    fun saveLoginModel(loginModel: LoginModel?): Boolean {
        loginModel?.let {
            sharedPreferences.edit().putString("userId", it.userId).apply()
            sharedPreferences.edit().putString("userPw", it.userPassword).apply()
            return true
        }
        return false
    }


    @SuppressLint("ApplySharedPref")
    fun deleteLogin() {
        runBlocking {
            sharedPreferences.edit().remove("userId").commit()
            sharedPreferences.edit().remove("userPw").commit()
        }
    }


}