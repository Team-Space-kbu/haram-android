package com.space.shared.common.exception

class InvalidTokenException(
    override val message: String
) : RuntimeException(message)