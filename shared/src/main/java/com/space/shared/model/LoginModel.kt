package com.space.shared.model

import android.text.Editable

data class LoginModel(
    val userId: String? = null,
    val userPassword: String? = null
){
    fun makeLoginModel(username: String, password: String): LoginModel =
        LoginModel(username, password)
}