package com.space.data.service.class_room

import com.space.shared.data.class_room.Course

interface ClassRoomService  {
    suspend fun getClassRoom(): List<String>

    suspend fun getClassRoomDetail(search: String): List<String>

    suspend fun getClassRoomInfo(course: String): List<Course>
}