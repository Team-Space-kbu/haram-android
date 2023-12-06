package com.space.shared.common.exception


class InvalidIntranetException(
    override val message: String
) : RuntimeException(message)