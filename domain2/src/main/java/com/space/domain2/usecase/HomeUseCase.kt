package com.space.domain2.usecase

import com.space.data.response.home.HomeInfo
import com.space.domain.base.NonParamUseCase
import com.space.domain2.api.SpaceHomeApi
import com.space.shared.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val spaceHomeApi: SpaceHomeApi,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : NonParamUseCase<HomeInfo>(dispatcher) {
    override suspend fun execute(): HomeInfo {
        return spaceHomeApi.getHome().data
    }
}