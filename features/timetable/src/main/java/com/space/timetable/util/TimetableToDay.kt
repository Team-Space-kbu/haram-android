package com.space.timetable.util

import com.islandparadise14.mintable.model.ScheduleDay
import com.islandparadise14.mintable.model.ScheduleEntity
import com.space.shared.data.timetable.Timetable



fun Timetable.toScheduleEntity(
    index: Int,
    color: String
): ScheduleEntity {
    fun toDay(timetable: Timetable): Int {
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
    return ScheduleEntity(
        index,                              //originId
        subject,                     //scheduleName
        classRoomLocation,           //roomInfo
        toDay(this),         //ScheduleDay object (MONDAY ~ SUNDAY)
        startTime,                    //startTime format: "HH:mm"
        endTime,                     //endTime  format: "HH:mm"
        color,                       //backgroundColor (optional)
        "#FFFFFF"
    )
}