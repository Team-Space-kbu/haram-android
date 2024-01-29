package com.space.timetable.util

import com.islandparadise14.mintable.model.ScheduleDay
import com.islandparadise14.mintable.model.ScheduleEntity
import com.space.shared.data.timetable.Timetable

fun Timetable.toDay(timetable: Timetable): Int {
    return when (timetable.lectureDay) {
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
}

fun Timetable.toScheduleEntity(
    index: Int,
    entity: Timetable,
    color: String
): ScheduleEntity {
    return ScheduleEntity(
        index,                              //originId
        entity.subject,                     //scheduleName
        entity.classRoomLocation,           //roomInfo
        entity.toDay(entity),               //ScheduleDay object (MONDAY ~ SUNDAY)
        entity.startTime,                    //startTime format: "HH:mm"
        entity.endTime,                     //endTime  format: "HH:mm"
        color,    //backgroundColor (optional)
        "#FFFFFF"
    )
}