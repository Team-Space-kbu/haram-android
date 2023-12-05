package com.space.repository.service

import com.space.repository.MileageService
import com.space.repository.api.MileageApi
import com.space.shared.data.mileage.MileageInfo
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class MileageServiceImpl @Inject constructor(
    private val mileageApi: MileageApi,
) : MileageService {
    override suspend fun getMileage(): MileageInfo {
      return runBlocking {
          mileageApi.getMileage().data
      }
    }
}