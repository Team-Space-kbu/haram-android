package com.space.shared.exception


class InvalidIntranetException(
    override val message: String
) : RuntimeException(message)