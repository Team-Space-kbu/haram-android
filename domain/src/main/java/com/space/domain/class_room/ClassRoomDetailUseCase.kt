package com.space.domain.class_room

import com.space.data.service.class_room.ClassRoomService
import com.space.domain.UseCase
import com.space.shared.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ClassRoomDetailUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val classRoomService: ClassRoomService
) : UseCase<String,List<String>>(dispatcher) {

    override suspend fun execute(param: String): List<String> {
        return classRoomService.getClassRoomDetail(param)
    }
}