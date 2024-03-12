package com.space.data.service.user

import com.space.shared.data.auth.User

interface UserService {
    suspend fun getUser(userId: String): User
}