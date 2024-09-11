package com.space.domain.class_room

import com.space.data.service.class_room.ClassRoomService
import com.space.domain.NonParamUseCase
import com.space.shared.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ClassRoomUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val classRoomService: ClassRoomService
) : NonParamUseCase<List<String>>(dispatcher) {

    override suspend fun execute(): List<String> {
        return classRoomService.getClassRoom()
    }
}