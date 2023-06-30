package com.space.haram_android.usecase.function.chapel

import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.response.intranet.ChapelInfoReq
import com.space.haram_android.common.data.response.intranet.ChapelListRes
import com.space.haram_android.common.data.response.intranet.IntranetTokenRes
import com.space.haram_android.common.token.IntranetManager
import com.space.haram_android.service.IntranetService
import java.lang.Exception
import javax.inject.Inject


class ChapelRepositoryImpl @Inject constructor(
    private val intranetManager: IntranetManager,
    private val intranetService: IntranetService
) : ChapelRepository {

    override fun getIntranetTokenData(): IntranetTokenRes {
        return intranetManager.getIntranetToken()
    }

    override suspend fun getChapelInfo(tokenRes: IntranetTokenRes): ResultData<ChapelInfoReq> {
        val response = intranetService.getChapelInfo(tokenRes)
        return if (response.isSuccessful) {
            ResultData.Success(response.body()?.data!!)
        } else
            ResultData.Error(Exception("알 수 없는 오류가 발생했습니다."))
    }

    override suspend fun getChapelList(tokenRes: IntranetTokenRes): ResultData<List<ChapelListRes>> {
        val response = intranetService.getChapelList(tokenRes)
        return if (response.isSuccessful) {
            ResultData.Success(response.body()?.data!!)
        } else
            ResultData.Error(Exception("알 수 없는 오류가 발생했습니다."))
    }
}