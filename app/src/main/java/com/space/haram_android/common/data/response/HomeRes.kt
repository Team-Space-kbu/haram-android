package com.space.haram_android.common.data.response

import com.space.haram_android.common.data.response.home.HomeBannerModel
import com.space.haram_android.common.data.response.home.HomeNewsModel
import com.space.haram_android.common.data.response.home.HomeNoticeModel

data class HomeRes(
    val notice: HomeNoticeModel,
    val banner: HomeBannerModel,
    val kokkoks: HomeNewsModel
)