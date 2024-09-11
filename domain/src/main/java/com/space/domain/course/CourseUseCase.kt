package com.space.domain.course

import com.space.data.service.class_room.CourseService
import com.space.domain.NonParamUseCase
import com.space.shared.annotation.IoDispatcher
import com.space.shared.data.course.CourseHome
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class CourseUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val courseService: CourseService
) : NonParamUseCase<List<CourseHome>>(dispatcher) {

    override suspend fun execute(): List<CourseHome> {
        return courseService.getCourse()
    }
}