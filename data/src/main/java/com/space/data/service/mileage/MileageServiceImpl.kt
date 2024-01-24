package com.space.data.service.mileage

import com.space.data.rest.MileageApi
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