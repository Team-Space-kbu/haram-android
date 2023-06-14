package com.space.haram_android.repository.function.chapel

import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.model.LoginIntranetModel
import com.space.haram_android.common.data.response.intranet.ChapelInfoReq
import com.space.haram_android.common.data.response.intranet.ChapelListRes
import com.space.haram_android.common.data.response.intranet.IntranetTokenRes
import com.space.haram_android.repository.ResponseBody

interface ChapelRepository {
    fun getIntranetTokenData(): IntranetTokenRes

    suspend fun getChapelInfo(tokenRes: IntranetTokenRes): ResultData<ChapelInfoReq>

    suspend fun getChapelList(tokenRes: IntranetTokenRes): ResultData<List<ChapelListRes>>
}