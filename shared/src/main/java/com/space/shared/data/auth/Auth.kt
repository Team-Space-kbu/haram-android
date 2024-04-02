package com.space.shared.data.auth

import com.space.shared.SpaceBody
import retrofit2.Response

data class Auth(
    val status: AuthStatus,
    val authToken: AuthToken? = null,
    val response: Response<SpaceBody<AuthToken>>? = null
)
