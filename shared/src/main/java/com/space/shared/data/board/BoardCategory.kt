package com.space.shared.data.board

import kotlinx.serialization.Serializable

@Serializable
data class BoardCategory(
    val iconPath: String,
    val categoryName: String,
    val boardType: String
)
