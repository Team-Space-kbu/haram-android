package com.space.domain.timetable

import com.space.data.service.timetable.TimetableService
import com.space.domain.NonParamUseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.timetable.Timetable
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class TimetableUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val timetableService: TimetableService
) : NonParamUseCase<List<Timetable>>(dispatcher) {
    override suspend fun execute(): List<Timetable> {
        return timetableService.getTimetable()
    }
}