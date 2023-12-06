package com.space.domain.usecase.mileage

import com.space.domain.base.NonParamUseCase
import com.space.repository.MileageService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.mileage.MileageInfo
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MileageUseCase @Inject constructor(
    private val mileageService: MileageService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : NonParamUseCase<MileageInfo>(dispatcher) {
    override suspend fun execute(): MileageInfo {
        return mileageService.getMileage()
    }
}