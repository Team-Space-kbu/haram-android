package com.space.data.service.class_room

import com.space.data.rest.ClassRoomApi
import com.space.shared.data.class_room.Course
import javax.inject.Inject

class ClassRoomServiceImpl @Inject constructor(
    private val classRoomApi: ClassRoomApi
) : ClassRoomService {
    override suspend fun getClassRoom(): List<String> {
        return classRoomApi.getClassRoom().data
    }

    override suspend fun getClassRoomDetail(string: String): List<String> {
        return classRoomApi.getClassRoomBuilding(string).data
    }

    override suspend fun getClassRoomInfo(course: String): List<Course> {
        return classRoomApi.getClassRoomDetail(course).data
    }

}