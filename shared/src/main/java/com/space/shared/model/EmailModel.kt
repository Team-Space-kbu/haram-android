package com.space.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class EmailModel(
    val email: String,
    val code: String
){
    fun isValidEmail(): Boolean {
        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")
        return emailRegex.matches(email)
    }

    fun getEmailModel(): String {
        return "$email@bible.ac.kr"
    }
}
