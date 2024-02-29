package com.space.shared.model

data class LoginModel(
    val userId: String? = null,
    val userPassword: String? = null,
    val uuid: String? = null,
    val deviceInfo: Device? = null
)

data class Device(
    val maker: String,
    val model: String,
    val osType: String,
    val osVersion: String
)