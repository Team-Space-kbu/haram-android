package com.space.repository.service.inf

import com.space.shared.data.mileage.MileageInfo

interface MileageService {
    suspend fun getMileage(): MileageInfo
}