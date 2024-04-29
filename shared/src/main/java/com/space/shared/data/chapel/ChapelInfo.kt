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
            (regulateDays.toInt() - confirmationDays.toInt()).toString()
        } catch (e: Throwable) {
            Timber.i(e.message)
            "-99"
        }
    }

}

