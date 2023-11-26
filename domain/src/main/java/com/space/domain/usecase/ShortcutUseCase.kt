package com.space.domain.usecase

import data.home.Shortcut
import com.space.domain.base.NonParamUseCase
import com.space.repository.HomeService
import com.space.shared.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ShortcutUseCase @Inject constructor(
    private val homeApi: HomeService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : NonParamUseCase<List<Shortcut>>(dispatcher) {
    override suspend fun execute(): List<Shortcut> {
        return homeApi.getShortcut()
    }
}