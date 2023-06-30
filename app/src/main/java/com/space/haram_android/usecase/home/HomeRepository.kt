package com.space.haram_android.usecase.home

import com.space.haram_android.common.data.ResultData
import com.space.haram_android.usecase.ResponseBody
import com.space.haram_android.common.data.response.home.HomeRes


interface HomeRepository {

    fun getHome(): ResultData<ResponseBody<HomeRes>>
}