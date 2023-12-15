package com.space.shared.data.auth

data class Auth(
    val status: AuthStatus,
    val authToken: AuthToken? = null,
)
