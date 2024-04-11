package com.space.shared.data.bible

import kotlinx.serialization.Serializable

@Serializable
data class SelectorBible(
    val status: Boolean,
    val chapter: List<String>? = emptyList(),
    val verse: Int = 0
)
