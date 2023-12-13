package com.space.shared.data.bible

import kotlinx.serialization.Serializable

@Serializable
data class BibleDetail(
    val chapter: String,
    val verse: Int,
)
