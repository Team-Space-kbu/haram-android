package com.space.shared

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> T.encodeToString(): String =
    Json.encodeToString(this)

inline fun <reified T> String.decodeFromString(): T =
    Json.decodeFromString(this)