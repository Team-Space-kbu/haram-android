package com.space.shared.data.home

import com.space.shared.data.chapel.ChapelInfo
import com.space.shared.model.home.HomeModel

data class HomeInfo(
    val notice: List<Notice>,
    val kokkoks: List<Kokkos>
) {
    fun toHomeModel(chapel: Pair<Boolean, ChapelInfo>?): HomeModel {
        return HomeModel(notice, kokkoks, chapel)
    }
}