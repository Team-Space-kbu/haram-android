package com.space.domain.usecase.timetable

import com.space.data.service.timetable.TimetableService
import com.space.domain.base.NonParamUseCase
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