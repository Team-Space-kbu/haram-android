package com.space.shared.data.board

import kotlinx.serialization.Serializable

@Serializable
data class BoardCategory(
    val categorySeq: Int,
    val sortNum: Int,
    val categoryName: String,
    val writeableBoard: Boolean,
    val writeableComment: Boolean,
    val writeableAnonymous: Boolean,
    val iconUrl: String
)
