package com.space.domain.course

import com.space.data.service.class_room.CourseService
import com.space.domain.UseCase
import com.space.shared.annotation.IoDispatcher
import com.space.shared.data.course.Course
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CourseDetailsUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val courseService: CourseService
) : UseCase<String, List<Course>>(dispatcher) {

    override suspend fun execute(params: String): List<Course> {
        return courseService.getCourseDetail(params)
    }
}