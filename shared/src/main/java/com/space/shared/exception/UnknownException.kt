package com.space.shared.exception

class UnknownException (
    override val message: String
) : RuntimeException(message)