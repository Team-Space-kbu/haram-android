package com.space.shared.exception.signup

class PasswordIncorrectCode (
    override val message: String?,
) : RuntimeException(message)