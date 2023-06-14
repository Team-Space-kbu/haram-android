package com.space.haram_android.repository.home

import com.space.haram_android.common.data.ResultData
import com.space.haram_android.repository.ResponseBody
import com.space.haram_android.common.data.response.home.HomeRes


interface HomeRepository {

    fun getHome(): ResultData<ResponseBody<HomeRes>>
}