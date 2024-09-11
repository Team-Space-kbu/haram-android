package com.space.domain.class_room

import com.space.data.service.class_room.ClassRoomService
import com.space.domain.UseCase
import com.space.shared.annotation.IoDispatcher
import com.space.shared.data.class_room.Course
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ClassRoomInfoUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val classRoomService: ClassRoomService
) : UseCase<String,List<Course>>(dispatcher) {

    override suspend fun execute(param: String): List<Course> {
        return classRoomService.getClassRoomInfo(param)
    }
}