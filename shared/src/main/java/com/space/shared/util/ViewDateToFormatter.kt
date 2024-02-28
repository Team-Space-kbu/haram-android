package com.space.shared.util

import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun formatToDate(inputDateString: String): String {
    val inputFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    val inputFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return try {
        LocalDateTime.parse(inputDateString, inputFormatter1).format(outputFormatter)
    } catch (e: Exception) {
        Timber.w(e.message)
        LocalDateTime.parse(inputDateString, inputFormatter2).format(outputFormatter)
    }
}
