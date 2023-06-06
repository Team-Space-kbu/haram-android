package com.space.haram_android.repository.home

import com.space.haram_android.repository.ResponseBody
import com.space.haram_android.common.data.response.HomeRes
import com.space.haram_android.service.HomeService
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService
) : HomeRepository {


    override suspend fun getHome(): Response<ResponseBody<HomeRes>> {
        return homeService.getHome()
    }
}