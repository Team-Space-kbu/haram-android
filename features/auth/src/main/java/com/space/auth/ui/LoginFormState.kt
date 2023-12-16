package com.space.auth.ui

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val loginFail: Boolean = true,
    val isTokenValid: Boolean = false,
    val statusLogin: Boolean = false
)