package com.space.shared.data.auth

import kotlinx.serialization.Serializable

@Serializable
enum class AuthType {
    LOGIN,
    INTRANET
}