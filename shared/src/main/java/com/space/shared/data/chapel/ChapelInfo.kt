package com.space.shared.data.chapel

import timber.log.Timber

data class ChapelInfo(
    val regulateDays: String,
    val attendanceDays: String,
    val lateDays: String,
    val confirmationDays: String,
    val entireDays: String
) {

    fun countDaysRemaining(): String {
        return try {
            val days = (regulateDays.toInt() - confirmationDays.toInt())
            if (days <= 0){
                "0"
            } else {
                days.toString()
            }
        } catch (e: Throwable) {
            Timber.i(e.message)
            "-99"
        }
    }

}

