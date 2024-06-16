package com.space.shared.model.home

import com.space.shared.data.chapel.ChapelInfo
import com.space.shared.data.home.Kokkos
import com.space.shared.data.home.Notice
import com.space.shared.data.home.Shortcut
import com.space.shared.data.home.Slider

data class HomeModel(
    val notice: List<Notice>,
    val slider: List<Slider>,
    val shortcut: List<Shortcut>,
    val kokkos: List<Kokkos>,
    val chapel: Pair<Boolean, ChapelInfo>
) {
    fun noticeLength(): Int = notice.size
    fun sliderLength(): Int = slider.size
    fun kokkoksLength(): Int = kokkos.size

}