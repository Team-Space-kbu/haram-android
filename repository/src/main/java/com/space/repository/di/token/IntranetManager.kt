package com.space.repository.di.token

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.space.shared.model.LoginIntranetModel
import com.space.shared.common.annotation.AuthEncrypted
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




    @SuppressLint("ApplySharedPref")
    fun deleteLogin() {
        runBlocking {
            sharedPreferences.edit().remove("userId").commit()
            sharedPreferences.edit().remove("userPw").commit()
        }
    }
}