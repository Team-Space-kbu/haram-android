package com.space.shared.data.board

import kotlinx.serialization.Serializable

@Serializable
data class BoardDetailNum(
    val categorySeq: Int,
    val boardSeq: Int,
    val writeableAnonymous : Boolean
)
