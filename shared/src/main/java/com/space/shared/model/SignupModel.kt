package com.space.shared.model

import java.util.regex.Pattern

data class SignupModel(
    val userId: String,
    var userEmail: String,
    val userPassword: String,
    val userNickname: String,
    val emailAuthCode: String
){
    fun isValidUserId(): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z0-9]{4,30}$")
        return pattern.matcher(userId).matches()
    }

    fun isValidUserEmail(): Boolean {
        return userEmail.endsWith("@bible.ac.kr")
    }

    fun isValidUserPassword(): Boolean {
//        val pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,255}$")
//        return pattern.matcher(userPassword).matches()
        return true
    }

    fun isValidUserNickname(): Boolean {
        val pattern = Pattern.compile("^[가-힣a-zA-Z0-9]{2,15}$")
        return pattern.matcher(userNickname).matches()
    }

    fun isValidEmailAuthCode(): Boolean {
        return emailAuthCode.length == 6
    }


}
