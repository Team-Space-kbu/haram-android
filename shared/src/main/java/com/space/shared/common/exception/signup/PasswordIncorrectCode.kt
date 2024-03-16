package com.space.shared.common.exception.signup

class PasswordIncorrectCode (
    override val message: String?,
) : RuntimeException(message)