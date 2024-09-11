package com.space.domain.course

import com.space.data.service.class_room.CourseService
import com.space.domain.UseCase
import com.space.shared.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ClassRoomDetailUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val courseService: CourseService
) : UseCase<String,List<String>>(dispatcher) {

    override suspend fun execute(param: String): List<String> {
        return courseService.getClassRoomDetail(param)
    }
}