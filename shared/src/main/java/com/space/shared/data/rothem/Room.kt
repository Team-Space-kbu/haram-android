package com.space.shared.data.rothem


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

data class Room(
    val roomSeq: Int,
    val thumbnailPath: String,
    val roomName: Boolean,
    val roomExplanation: Boolean,
    val location: Boolean,
    val peopleCount: Boolean,
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
