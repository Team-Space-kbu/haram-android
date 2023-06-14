package com.space.haram_android.common.data.response.home

import com.space.haram_android.common.data.response.home.data.HomeBannerModel
import com.space.haram_android.common.data.response.home.data.HomeNewsModel
import com.space.haram_android.common.data.response.home.data.HomeNoticeModel

data class HomeRes(
    val notice: HomeNoticeModel,
    val banner: HomeBannerModel,
    val kokkoks: HomeNewsModel
)