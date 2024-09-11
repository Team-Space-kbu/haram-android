package com.space.domain.course

import com.space.data.service.class_room.CourseService
import com.space.domain.NonParamUseCase
import com.space.shared.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ClassRoomUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val courseService: CourseService
) : NonParamUseCase<List<String>>(dispatcher) {

    override suspend fun execute(): List<String> {
        return courseService.getClassRoom()
    }
}