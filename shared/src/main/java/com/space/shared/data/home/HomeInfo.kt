package com.space.shared.data.home

import com.space.shared.data.chapel.ChapelInfo
import com.space.shared.model.home.HomeModel
import timber.log.Timber

data class HomeInfo(
    val notice: NoticeIndex,
    val banner: SliderIndex,
    val kokkoks: KokkosIndex
) {
    fun toHomeModel(chapel: Pair<Boolean, ChapelInfo>): HomeModel {
        if (
            notice.index != notice.notices.size &&
            banner.index != banner.banners.size &&
            kokkoks.index != kokkoks.kokkoksNews.size
        ) {
            Timber.w("Does not match server data!!")
        }
        return HomeModel(
            notice.notices,
            banner.banners,
            emptyList(),
            kokkoks.kokkoksNews,
            chapel
        )
    }
}