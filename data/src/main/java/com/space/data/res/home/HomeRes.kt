package com.space.data.res.home

import com.space.data.res.home.data.HomeBannerModel
import com.space.data.res.home.data.HomeNewsModel
import com.space.data.res.home.data.HomeNoticeModel


data class HomeRes(
    val notice: HomeNoticeModel,
    val banner: HomeBannerModel,
    val kokkoks: HomeNewsModel
)