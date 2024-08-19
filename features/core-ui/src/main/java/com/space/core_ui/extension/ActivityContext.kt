package com.space.core_ui.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.os.bundleOf

inline fun <reified T : Activity> Context.buildIntent(
    vararg argument: Pair<String, Any?>
): Intent = Intent(this, T::class.java).apply {
    putExtras(bundleOf(*argument))
}

inline fun <reified T : Activity> Context.buildIntent(): Intent =
    Intent(this, T::class.java)


inline fun <reified T : Activity> Context.startActivity(
    vararg argument: Pair<String, Any?>
) {
    startActivity(buildIntent<T>(*argument))
}

inline fun <reified T : Activity> Context.cleanActivity(
    vararg argument: Pair<String, Any?>
) {
    val intent = buildIntent<T>(*argument).apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }
    startActivity(intent)
}


