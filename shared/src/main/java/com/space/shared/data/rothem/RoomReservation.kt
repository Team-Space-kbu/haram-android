package com.space.shared.data.rothem

data class RoomReservation(
    val roomResponse: RothemRoom,
    val policyResponses: List<RothemPolicy>,
    val calendarResponses: List<ReservationCalendar>
)

data class RothemPolicy(
    val policySeq:Int,
)

data class ReservationCalendar(
    val calendarSeq: Int,
    val day: String,
    val year: String,
    val month: String,
    val date: String,
    val isAvailable: Boolean,
    val times: List<RothemTime>
)

data class RothemTime(
    val timeSeq: Int,
    val hour: String,
    val minute: String,
    val meridiem: String,
    val isReserved: Boolean
)