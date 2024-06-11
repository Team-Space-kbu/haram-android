package com.space.domain.user

import com.space.data.service.auth.AuthService
import com.space.data.service.user.UserService
import com.space.domain.UseCase
import com.space.shared.annotation.IoDispatcher
import com.space.shared.model.PermutePasswordModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UserPasswordUseCase @Inject constructor(
    private val authService: AuthService,
    private val userService: UserService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : UseCase<PermutePasswordModel, Boolean>(dispatcher) {
    override suspend fun execute(param: PermutePasswordModel): Boolean {
        val userId = authService.getUserId()
        return userService.putPassword(userId, param)
    }

}