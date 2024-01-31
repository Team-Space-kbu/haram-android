package com.space.shared.data.rothem

data class Reservation(
    val reservationSeq: Int,
    val userId: String,
    val phoneNum: String,
    val reservationCode: String,
    val roomResponse: Room,
    val calendarResponse: UserCalenderReservation,
    val timeResponses: List<RothemTime>
)
