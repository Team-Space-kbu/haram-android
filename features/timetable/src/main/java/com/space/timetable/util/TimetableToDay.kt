package com.space.timetable.util

import com.islandparadise14.mintable.model.ScheduleDay
import com.space.shared.data.timetable.Timetable

fun Timetable.toDay(timetable: Timetable): Int =
    when (timetable.lectureDay) {
        "월" -> ScheduleDay.MONDAY
        "화" -> ScheduleDay.TUESDAY
        "수" -> ScheduleDay.WEDNESDAY
        "목" -> ScheduleDay.THURSDAY
        "금" -> ScheduleDay.FRIDAY
        "토" -> ScheduleDay.SATURDAY
        "일" -> ScheduleDay.SUNDAY
        else -> {
            0
        }
    }