package com.space.domain.usecase.intranet

import com.space.data.ResponseBody
import com.space.data.ResultData
import com.space.data.model.LoginIntranetModel
import com.space.data.response.intranet.IntranetTokenRes

interface IntranetRepository {

    fun getIntranetTokenData(): IntranetTokenRes

    fun getIntranetIdModel(): LoginIntranetModel

    fun saveIntranetToken(intranetTokenRes: IntranetTokenRes)

    fun saveIntranetModel(intranetModel: LoginIntranetModel)

    suspend fun isInvalidToken(intranetTokenRes: IntranetTokenRes): ResultData<Boolean>

    suspend fun getIntranetLogin(
        intranetModel: LoginIntranetModel,
        intranetTokenRes: IntranetTokenRes
    ): ResultData<Boolean>

    suspend fun getIntranetToken(): ResultData<ResponseBody<IntranetTokenRes>>
}