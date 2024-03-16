package com.space.data.service.intranet

import com.space.data.rest.IntranetApi
import com.space.shared.SpaceBody
import com.space.shared.common.exception.AlreadyRegisteredException
import com.space.shared.common.exception.NotMatchIntranetException
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
                        throw AlreadyRegisteredException("This information has already been registered on the server.")
                    }
                    460 ->{
                        throw NotMatchIntranetException("Student information cannot be obtained from the server.")
                    }
                    else->{
                        throw e
                    }
                }
            }
        }
    }
}