package com.space.shared.data.rothem

import kotlinx.serialization.Serializable


data class RoomDetail(
    val roomResponse: Room? = null,
    val roomFileResponses: List<RoomFile>? = null,
    val amenityResponses: List<Amenity>? = null
)

data class RoomFile(
    val seq: Int? = null,
    val roomSeq: Int? = null,
    val sortNum: Int? = null,
    val filePath: String? = null,
)

@Serializable
data class Room(
    val roomSeq: Int? = null,
    val thumbnailPath: String? = null,
    val roomName: String? = null,
    val roomExplanation: String? = null,
    val location: String? = null,
    val peopleCount: Int? = null,
)

data class Amenity(
    val amenitySeq: Int? = null,
    val title: String? = null,
    val filePath: String? = null,
)
