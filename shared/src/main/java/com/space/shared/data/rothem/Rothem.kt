package com.space.shared.data.rothem

data class Rothem(
    val roomResponses: List<Room>,
    val isReserved: Int
)

data class RothemRoom(
    val policySeq: Int,
    val title: String,
    val isRequired: Boolean,
    val createdBy: String,
    val createdAt: String,
    val modifiedBy: String,
    val modifiedAt: String
)