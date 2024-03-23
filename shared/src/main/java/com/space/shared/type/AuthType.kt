package com.space.shared.type

import kotlinx.serialization.Serializable

@Serializable
enum class AuthType {
    LOGIN,
    INTRANET
}