package com.space.shared.data.rothem

data class RoomReservation(
    val roomResponse: Room,
    val policyResponses: List<RothemPolicy>,
    val calendarResponses: List<ReservationCalendar>
)

data class RothemPolicy(
    val policySeq: Int,
    val title: String,
    val content: String,
    val isRequired: Boolean,
    val createdBy: String,
    val createdAt: String,
    val modifiedBy: String,
    val modifiedAt: String
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