package com.space.shared.data.rothem

data class RothemHome(
    val noticeResponses: List<RothemNotice>? = emptyList(),
    val roomResponses: List<Room>? = emptyList(),
    val isReserved: Int? = 0
)
