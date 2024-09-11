package com.space.data.service.class_room

import com.space.shared.data.course.Course
import com.space.shared.data.course.CourseHome


interface CourseService {
    suspend fun getClassRoom(): List<String>

    suspend fun getClassRoomDetail(search: String): List<String>

    suspend fun getClassRoomInfo(course: String): List<Course>

    suspend fun getCourse(): List<CourseHome>

    suspend fun getCourseDetail(course: String): List<Course>
}