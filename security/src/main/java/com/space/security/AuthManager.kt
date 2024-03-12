package com.space.security

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.space.security.di.DeviceSecure
import com.space.shared.model.LoginModel
import com.space.shared.common.annotation.AuthEncrypted
import com.space.shared.model.AuthModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AuthManager @Inject constructor(
    @AuthEncrypted private val sharedPreferences: SharedPreferences,
    private val deviceSecure: DeviceSecure
) {
    fun getUserId(): String? {
        return sharedPreferences.getString("userId", "")
    }


    fun getRefreshModel(): AuthModel = AuthModel(
        sharedPreferences.getString("userId", ""),
        deviceSecure.ssid
    )

    fun getLoginModel(): LoginModel = LoginModel(
        sharedPreferences.getString("userId", null),
        sharedPreferences.getString("userPw", null),
        deviceSecure.ssid,
        deviceSecure.device
    )


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