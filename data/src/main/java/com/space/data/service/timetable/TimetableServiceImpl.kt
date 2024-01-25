package com.space.data.service.timetable

import com.space.data.rest.TimetableApi
import com.space.shared.data.timetable.Timetable
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class TimetableServiceImpl @Inject constructor(
    private val timetableApi: TimetableApi
) : TimetableService {
    override suspend fun getTimetable(): List<Timetable> {
        return runBlocking {
            timetableApi.getTimetable().data
        }
    }
}