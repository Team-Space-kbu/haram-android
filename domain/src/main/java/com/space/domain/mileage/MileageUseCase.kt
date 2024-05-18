package com.space.domain.mileage

import com.space.domain.NonParamUseCase
import com.space.data.service.mileage.MileageService
import com.space.builder_annotation.annotation.IoDispatcher
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