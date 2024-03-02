package com.space.data.service.intranet

import com.space.data.rest.IntranetApi
import com.space.shared.SpaceBody
import com.space.shared.common.exception.AlreadyRegistered
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
                Timber.i(e.message())
                when(e.code()){
                    400 ->{
                        throw AlreadyRegistered("This information has already been registered on the server.")
                    }
                    460 ->{
                        throw NotMatchIntranet("Student information cannot be obtained from the server.")
                    }
                    else->{
                        throw e
                    }
                }
            }
        }
    }
}