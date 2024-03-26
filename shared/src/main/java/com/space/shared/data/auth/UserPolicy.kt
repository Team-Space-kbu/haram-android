package com.space.shared.data.auth

data class UserPolicy(
    val termsSeq: Int,
    val title: String,
    val content: String,
    val isRequired: Boolean
)
