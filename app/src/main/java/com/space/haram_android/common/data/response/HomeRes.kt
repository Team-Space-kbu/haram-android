package com.space.haram_android.common.data.response

import com.space.haram_android.common.data.model.home.HomeBannerModel
import com.space.haram_android.common.data.model.home.HomeNewsModel
import com.space.haram_android.common.data.model.home.HomeNoticeModel

data class HomeRes(
    val notice: HomeNoticeModel,
    val banner: HomeBannerModel,
    val kokkoks: HomeNewsModel
)