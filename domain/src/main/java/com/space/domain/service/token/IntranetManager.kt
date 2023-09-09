package com.space.domain.service.token

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.space.data.model.LoginIntranetModel
import com.space.data.res.intranet.IntranetTokenRes
import com.space.shared.annotation.AuthEncrypted
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class IntranetManager @Inject constructor(
    @AuthEncrypted private val sharedPreferences: SharedPreferences
) {

    fun getIntranetIdModel(): LoginIntranetModel {
        return LoginIntranetModel(
            null,
            sharedPreferences.getString("intranetId", null),
            sharedPreferences.getString("intranetPw", null)
        )
    }

    fun saveIntranetModel(intranetModel: LoginIntranetModel) {
        intranetModel.let {
            sharedPreferences.edit().putString("intranetId", it.id).apply()
            sharedPreferences.edit().putString("intranetPw", it.pw).apply()
        }
    }


    fun getIntranetToken(): IntranetTokenRes {
        return IntranetTokenRes(
            sharedPreferences.getString("token", null),
            sharedPreferences.getString("xsrf", null),
            sharedPreferences.getString("laravel", null),
        )
    }

    fun saveIntranetToken(intranetTokenRes: IntranetTokenRes) {
        intranetTokenRes.let {
            sharedPreferences.edit().putString("token", it.token).apply()
            sharedPreferences.edit().putString("xsrf", it.xsrf_token).apply()
            sharedPreferences.edit().putString("laravel", it.laravel_session).apply()
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