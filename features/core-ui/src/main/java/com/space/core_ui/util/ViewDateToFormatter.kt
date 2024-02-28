package com.space.core_ui.util

import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun dateToDateTime(date: String): String {
    date.let {
        val dateTime = parsedDate(date)
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH시 mm분"))
    }
}

fun parsedDate(string: String): LocalDateTime {
    val formatter = when {
        string.contains('T') -> DateTimeFormatter.ofPattern("yyyy.MM.dd HH시 mm분")
        string.contains('-') -> DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        string.length == 14 -> DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
        else -> DateTimeFormatter.ofPattern("yyyyMMdd")
    }
    return when {
        string.contains('T') -> OffsetDateTime.parse(string).toLocalDateTime()
        string.contains('-') || string.length == 14 -> LocalDateTime.parse(string, formatter)
        else -> LocalDateTime.parse(string + "000000", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
    }
}