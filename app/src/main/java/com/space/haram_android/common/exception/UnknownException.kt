package com.space.haram_android.common.exception

class UnknownException (
    override val message: String
) : RuntimeException(message)