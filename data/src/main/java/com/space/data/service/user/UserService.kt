package com.space.data.service.user

import com.space.shared.data.auth.User
import com.space.shared.model.PermutePasswordModel
import retrofit2.http.Body
import retrofit2.http.Path

interface UserService {
    suspend fun getUser(userId: String): User

    suspend fun putPassword(
        userId: String,
        password: PermutePasswordModel
    ): Boolean
}