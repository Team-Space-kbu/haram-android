package com.space.shared.exception

class IndexOutException(
    override val message: String? = null
) : RuntimeException(message)