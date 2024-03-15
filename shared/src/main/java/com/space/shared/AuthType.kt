package com.space.shared

import kotlinx.serialization.Serializable

@Serializable
enum class AuthType {
    LOGIN,
    INTRANET
}