package com.space.shared.data.rothem

import kotlinx.serialization.Serializable


data class RoomDetail(
    val roomResponse: Room,
    val roomFileResponses: List<RoomFile>,
    val amenityResponses: List<Amenity>
)

data class RoomFile(
    val seq: Int,
    val roomSeq: Int,
    val sortNum: Int,
    val filePath: String,
    val createdBy: String,
    val createdAt: String,
    val modifiedBy: String,
    val modifiedAt: String
)

@Serializable
data class Room(
    val roomSeq: Int,
    val thumbnailPath: String,
    val roomName: String,
    val roomExplanation: String,
    val location: String,
    val peopleCount: Int,
    val createdBy: String,
    val createdAt: String,
    val modifiedBy: String,
    val modifiedAt: String
)

data class Amenity(
    val amenitySeq: Int,
    val title: String,
    val filePath: String,
    val createdBy: String,
    val createdAt: String,
    val modifiedBy: String,
    val modifiedAt: String
)
