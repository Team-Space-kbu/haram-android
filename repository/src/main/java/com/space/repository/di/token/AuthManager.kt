package com.space.repository.di.token

import android.annotation.SuppressLint
import android.content.SharedPreferences
import model.LoginModel
import com.space.shared.annotation.AuthEncrypted
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


    fun saveLoginModel(loginModel: LoginModel) {
        loginModel.let {
            sharedPreferences.edit().putString("userId", it.userId).apply()
            sharedPreferences.edit().putString("userPw", it.userPassword).apply()
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