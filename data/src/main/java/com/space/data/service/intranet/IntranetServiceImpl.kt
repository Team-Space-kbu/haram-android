package com.space.data.service.intranet

import com.space.data.rest.IntranetApi
import com.space.shared.SpaceBody
import com.space.shared.common.exception.NotMatchIntranet
import com.space.shared.model.IntranetModel
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

internal class IntranetServiceImpl @Inject constructor(
    private val intranetApi: IntranetApi
) : IntranetService {
    override fun setIntranetId(intranetModel: IntranetModel): SpaceBody<String> {
        return runBlocking {
            try {
                intranetApi.postIntranet(intranetModel)
            } catch (e: HttpException) {
                if (e.code() == 460) {
                    Timber.i(e.message())
                    throw NotMatchIntranet("Student information cannot be obtained from the server.")
                }
                throw e
            }
        }
    }
}