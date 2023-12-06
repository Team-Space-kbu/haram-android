package com.space.shared.common.exception

class UnknownException (
    override val message: String
) : RuntimeException(message)