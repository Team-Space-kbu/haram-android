package com.space.shared.model.home

import com.space.shared.data.chapel.ChapelInfo
import com.space.shared.data.home.Kokkos
import com.space.shared.data.home.Notice

data class HomeModel(
    val notice: List<Notice> = emptyList(),
    val kokkos: List<Kokkos> = emptyList(),
    val chapel: Pair<Boolean, ChapelInfo>? = null
)