package com.space.shared.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun formatToDate(inputDateString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val inputDateTime = LocalDateTime.parse(inputDateString, inputFormatter)
    return inputDateTime.format(outputFormatter)
}
