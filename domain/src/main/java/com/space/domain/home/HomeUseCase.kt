package com.space.domain.home

import com.space.shared.data.home.HomeInfo
import com.space.domain.NonParamUseCase
import com.space.data.service.home.HomeService
import com.space.shared.common.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeApi: HomeService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : NonParamUseCase<HomeInfo>(dispatcher) {
    override suspend fun execute(): HomeInfo {
        return homeApi.getHome()
    }
}