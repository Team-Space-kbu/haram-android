package com.space.data.service.timetable

import com.space.shared.data.timetable.Timetable

interface TimetableService {
    suspend fun getTimetable(): List<Timetable>
}