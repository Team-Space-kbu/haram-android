package com.space.domain.usecase.home

import com.space.data.ResponseBody
import com.space.data.ResultData
import com.space.data.response.home.HomeRes


interface HomeRepository {

    fun getHome(): ResultData<ResponseBody<HomeRes>>
}