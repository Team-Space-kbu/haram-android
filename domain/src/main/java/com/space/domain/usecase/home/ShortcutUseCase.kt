package com.space.domain.usecase.home

import com.space.shared.data.home.Shortcut
import com.space.domain.NonParamUseCase
import com.space.data.service.home.HomeService
import com.space.shared.common.annotation.IoDispatcher
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