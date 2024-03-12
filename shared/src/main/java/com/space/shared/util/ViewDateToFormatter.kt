package com.space.shared.util

import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Formatter {
    val inputFormatter1: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    val inputFormatter2: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    val outputFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val pattern1 = Regex("""\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}[+-]\d{2}:\d{2}""")
    val pattern2 = Regex("""\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}""")
}


fun formatToDate(inputDateString: String): String {
    return when {
        Formatter.pattern1.matches(inputDateString) -> {
            LocalDateTime.parse(inputDateString, Formatter.inputFormatter1).format(Formatter.outputFormatter)
        }

        Formatter. pattern2.matches(inputDateString) -> {
            LocalDateTime.parse(inputDateString, Formatter.inputFormatter2).format(Formatter.outputFormatter)
        }

        else -> {
            Timber.e("The format is unknown.")
            inputDateString

        }
    }
}
