package com.space.shared.exception

class NotFoundStudentIdException(
    override val message: String?,
) : RuntimeException(message)