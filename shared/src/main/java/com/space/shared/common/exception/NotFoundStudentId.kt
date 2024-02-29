package com.space.shared.common.exception

class NotFoundStudentId(
    override val message: String?,
) : RuntimeException(message)