package com.space.shared.data.board

import kotlinx.serialization.Serializable

@Serializable
data class BoardCategory(
    val iconPath: String? = null,
    val categoryName: String? = null,
    val boardType: String? = null
)
