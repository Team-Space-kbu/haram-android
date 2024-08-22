package com.space.shared.util

import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object Formatter {
    private val inputFormatter1: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    private val inputFormatter2: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    private val outputFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val pattern1 = Regex("""\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}[+-]\d{2}:\d{2}""")
    private val pattern2 = Regex("""\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}""")

    @JvmStatic
    fun formatToDate(inputDateString: String): String {
        return when {
            pattern1.matches(inputDateString) -> {
                LocalDateTime.parse(inputDateString, inputFormatter1).format(outputFormatter)
            }

            pattern2.matches(inputDateString) -> {
                LocalDateTime.parse(inputDateString, inputFormatter2).format(outputFormatter)
            }

            else -> {
                Timber.e("The format is unknown.")
                inputDateString

            }
        }
    }
}

