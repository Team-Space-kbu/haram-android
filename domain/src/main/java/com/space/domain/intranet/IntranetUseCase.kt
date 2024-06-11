package com.space.domain.intranet

import com.space.data.service.intranet.IntranetService
import com.space.domain.UseCase
import com.space.shared.SpaceBody
import com.space.shared.annotation.IoDispatcher
import com.space.shared.model.IntranetModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class IntranetUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val intranetService: IntranetService
) : UseCase<IntranetModel, SpaceBody<String>>(dispatcher) {
    override suspend fun execute(param: IntranetModel): SpaceBody<String> {
        return intranetService.setIntranetId(param)
    }
}