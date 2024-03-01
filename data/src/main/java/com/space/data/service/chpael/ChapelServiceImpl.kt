package com.space.data.service.chpael

import com.space.data.rest.ChapelApi
import com.space.shared.common.exception.NotFoundStudentId
import com.space.shared.data.chapel.ChapelDetail
import com.space.shared.data.chapel.ChapelInfo
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class ChapelServiceImpl @Inject constructor(
    private val chapelApi: ChapelApi
) : ChapelService {
    override suspend fun getChapelInfo(): ChapelInfo {
        return runBlocking {
            try {
                chapelApi.getChapelInfo().data
            } catch (e: HttpException) {
                if (e.code() == 460) {
                    Timber.i(e.message())
                    throw NotFoundStudentId("Student information cannot be obtained from the server.")
                }
                throw e
            }
        }
    }

    override suspend fun getChapelDetail(): List<ChapelDetail> {
        return runBlocking {
            chapelApi.getChapelDetail().data
        }
    }
}