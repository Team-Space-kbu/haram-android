
package com.space.core_ui.extension

import androidx.fragment.app.Fragment

inline fun <reified T : Any> Fragment.extra(
    key: String,
    default: T? = null
) = lazy {
    val value = arguments?.getString(key)
    if (value is String) value else default
}

inline fun <reified T : Any> Fragment.extraNotNull(
    key: String,
    default: T? = null
) = lazy {
    val value = arguments?.getString(key)
    requireNotNull(if (value is T) value else default) { key }
}

fun <T, R> Lazy<T>.map(transform: (T) -> R): Lazy<R> {
    return lazy { transform(value) }
}
