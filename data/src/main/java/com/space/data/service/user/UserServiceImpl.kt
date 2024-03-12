package com.space.data.service.user

import com.space.data.rest.UserApi
import com.space.shared.data.auth.User
import javax.inject.Inject

internal class UserServiceImpl @Inject constructor(
    private val userApi: UserApi
) : UserService {
    override suspend fun getUser(userId: String): User {
        return userApi.getUser(userId).data
    }
}