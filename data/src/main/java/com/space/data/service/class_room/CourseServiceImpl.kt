package com.space.data.service.class_room

import com.space.data.rest.CourseApi
import com.space.shared.data.course.Course
import com.space.shared.data.course.CourseHome
import javax.inject.Inject

class CourseServiceImpl @Inject constructor(
    private val courseApi: CourseApi
) : CourseService {
    override suspend fun getClassRoom(): List<String> {
        return courseApi.getClassRoom().data
    }

    override suspend fun getClassRoomDetail(string: String): List<String> {
        return courseApi.getClassRoomBuilding(string).data
    }

    override suspend fun getClassRoomInfo(course: String): List<Course> {
        return courseApi.getClassRoomDetail(course).data
    }

    override suspend fun getCourse(): List<CourseHome> {
        return courseApi.getCourse().data
    }

    override suspend fun getCourseDetail(course: String): List<Course> {
        return courseApi.getCourseDetail(course).data
    }

}