package com.space.shared.data.home

import kotlinx.serialization.Serializable

@Serializable
data class Notice(
    val noticeSeq: String? = null,
    val thumbnailPath: String? = null,
    val title: String? = null
)
