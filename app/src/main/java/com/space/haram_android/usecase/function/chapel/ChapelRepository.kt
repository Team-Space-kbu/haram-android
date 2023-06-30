package com.space.haram_android.usecase.function.chapel

import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.response.intranet.ChapelInfoReq
import com.space.haram_android.common.data.response.intranet.ChapelListRes
import com.space.haram_android.common.data.response.intranet.IntranetTokenRes

interface ChapelRepository {
    fun getIntranetTokenData(): IntranetTokenRes

    suspend fun getChapelInfo(tokenRes: IntranetTokenRes): ResultData<ChapelInfoReq>

    suspend fun getChapelList(tokenRes: IntranetTokenRes): ResultData<List<ChapelListRes>>
}