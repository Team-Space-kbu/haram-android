package com.space.domain.home

import com.space.data.service.chpael.ChapelService
import com.space.domain.NonParamUseCase
import com.space.data.service.home.HomeService
import com.space.shared.annotation.IoDispatcher
import com.space.shared.data.chapel.ChapelInfo
import com.space.shared.model.home.HomeModel
import kotlinx.coroutines.CoroutineDispatcher
import java.time.LocalTime
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeApi: HomeService,
    private val chapelService: ChapelService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : NonParamUseCase<HomeModel>(dispatcher) {
    private fun chapelTime(): Boolean =
        LocalTime.now() in LocalTime.of(10, 0)..LocalTime.of(13, 0)

    override suspend fun execute(): HomeModel {
        val chapel = if (chapelTime()) {
            Pair(true, chapelService.getChapelInfo())
        } else {
            Pair(false, ChapelInfo("0", "0", "0", "0", "0"))
        }
        return homeApi.getHome().toHomeModel(chapel)
    }
}