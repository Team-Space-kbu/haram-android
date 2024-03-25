package com.space.domain.usecase.user

import com.space.data.service.auth.AuthService
import com.space.data.service.user.UserService
import com.space.domain.NonParamUseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.auth.User
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(
    private val authService: AuthService,
    private val userService: UserService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : NonParamUseCase<User>(dispatcher) {
    override suspend fun execute(): User {
        val userId = authService.getUserId()
        return userService.getUser(userId)
    }

}