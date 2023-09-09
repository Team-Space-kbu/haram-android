package com.space.domain.usecase.chapel

import com.space.data.ResultData
import com.space.data.res.intranet.ChapelInfoReq
import com.space.data.res.intranet.ChapelListRes
import com.space.data.res.intranet.IntranetTokenRes


interface ChapelRepository {
    fun getIntranetTokenData(): IntranetTokenRes

    suspend fun getChapelInfo(tokenRes: IntranetTokenRes): ResultData<ChapelInfoReq>

    suspend fun getChapelList(tokenRes: IntranetTokenRes): ResultData<List<ChapelListRes>>
}