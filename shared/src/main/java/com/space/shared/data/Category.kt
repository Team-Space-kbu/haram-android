package com.space.shared.data

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val categoryTitle: String,
    val categoryText: String,
    val detailName: String? = null
)
