package com.space.shared.data.timetable

data class Timetable(
    val semester: String,
    val lectureNum: String,
    val classRoomLocation: String,
    val lectureDay: String,
    val startTime: String,
    val endTime: String,
    val subject: String,
    val classRoomName: String
)