package com.space.shared.common.exception

class NotFoundStudentIdException(
    override val message: String?,
) : RuntimeException(message)