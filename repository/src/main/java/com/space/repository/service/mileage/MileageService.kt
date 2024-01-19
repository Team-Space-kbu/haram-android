package com.space.repository.service.mileage

import com.space.shared.data.mileage.MileageInfo

interface MileageService {
    suspend fun getMileage(): MileageInfo
}