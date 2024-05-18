package com.space.shared.exception.user

class PasswordDifferent(override val message: String?) : RuntimeException(message)