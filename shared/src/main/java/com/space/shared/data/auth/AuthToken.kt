package com.space.shared.data.auth

data class AuthToken(
    val accessToken: String,
    val refreshToken: String
)
