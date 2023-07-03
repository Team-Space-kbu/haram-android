package com.space.domain.usecase.function.chapel

import com.space.data.ResultData
import com.space.data.response.intranet.ChapelInfoReq
import com.space.data.response.intranet.ChapelListRes
import com.space.data.response.intranet.IntranetTokenRes


interface ChapelRepository {
    fun getIntranetTokenData(): IntranetTokenRes

    suspend fun getChapelInfo(tokenRes: IntranetTokenRes): ResultData<ChapelInfoReq>

    suspend fun getChapelList(tokenRes: IntranetTokenRes): ResultData<List<ChapelListRes>>
}