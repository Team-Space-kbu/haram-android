package com.space.haram_android.repository.intranet

import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.model.LoginIntranetModel
import com.space.haram_android.common.data.response.intranet.IntranetTokenRes
import com.space.haram_android.repository.ResponseBody

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