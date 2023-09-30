package com.space.domain.usecase.home

import com.space.data.ResponseBody
import com.space.data.ResultData
import com.space.data.res.home.HomeRes


interface HomeUseCase {

    fun getHome(): ResultData<ResponseBody<HomeRes>>
}