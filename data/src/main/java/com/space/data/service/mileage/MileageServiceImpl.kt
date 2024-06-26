package com.space.data.service.mileage

import com.space.data.rest.MileageApi
import com.space.shared.exception.NotFoundStudentIdException
import com.space.shared.data.mileage.MileageInfo
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

internal class MileageServiceImpl @Inject constructor(
    private val mileageApi: MileageApi,
) : MileageService {
    override suspend fun getMileage(): MileageInfo {
        return runBlocking {
            try {
                mileageApi.getMileage().data
            } catch (e: HttpException) {
                if (e.code() == 460) {
                    Timber.i(e.message())
                    throw com.space.shared.exception.NotFoundStudentIdException("Student information cannot be obtained from the server.")
                }
                throw e
            }
        }
    }
}