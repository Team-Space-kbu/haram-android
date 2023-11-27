package com.space.domain.usecase

import com.space.shared.result.ResultData
import com.space.shared.response.intranet.ChapelInfoReq
import com.space.shared.response.intranet.ChapelListRes
import com.space.shared.response.intranet.IntranetTokenRes
import com.space.domain.service.IntranetService
import com.space.repository.di.token.IntranetManager
import java.lang.Exception
import javax.inject.Inject


class ChapelUseCase @Inject constructor(
    private val intranetManager: IntranetManager,
    private val intranetService: IntranetService
)  {

    fun getIntranetTokenData(): IntranetTokenRes {
        return intranetManager.getIntranetToken()
    }

    suspend fun getChapelInfo(tokenRes: IntranetTokenRes): ResultData<ChapelInfoReq> {
        val response = intranetService.getChapelInfo(tokenRes)
        return if (response.isSuccessful) {
            ResultData.Success(response.body()?.data!!)
        } else
            ResultData.Error(Exception("알 수 없는 오류가 발생했습니다."))
    }

    suspend fun getChapelList(tokenRes: IntranetTokenRes): ResultData<List<ChapelListRes>> {
        val response = intranetService.getChapelList(tokenRes)
        return if (response.isSuccessful) {
            ResultData.Success(response.body()?.data!!)
        } else
            ResultData.Error(Exception("알 수 없는 오류가 발생했습니다."))
    }
}