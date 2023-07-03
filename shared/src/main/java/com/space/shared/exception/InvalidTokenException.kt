package com.space.shared.exception

class InvalidTokenException(
    override val message: String
) : RuntimeException(message)