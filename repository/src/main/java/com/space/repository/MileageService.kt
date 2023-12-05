package com.space.repository

import com.space.shared.data.mileage.MileageInfo

interface MileageService {
    suspend fun getMileage(): MileageInfo
}