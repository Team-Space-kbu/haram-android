package com.space.security

interface Authenticator {
    suspend fun signOut()
    suspend fun signInWithId(id: String, password: String)
    suspend fun signUpWithId(id: String, password: String)
    suspend fun canAutoSignIn(): Boolean
}