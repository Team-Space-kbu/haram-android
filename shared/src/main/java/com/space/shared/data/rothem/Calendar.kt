package com.space.shared.data.rothem

data class UserCalenderReservation(
    val calendarSeq: Int,
    val day: String,
    val year: String,
    val month: String,
    val date: String,
    val isAvailable: Boolean,
    val weekStatus: String
)


