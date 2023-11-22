package com.space.data.response.home

import com.space.data.response.home.data.HomeBannerModel
import com.space.data.response.home.data.HomeNewsModel
import com.space.data.response.home.data.HomeNoticeModel


data class HomeInfo(
    val notice: HomeNoticeModel,
    val banner: HomeBannerModel,
    val kokkoks: HomeNewsModel
)