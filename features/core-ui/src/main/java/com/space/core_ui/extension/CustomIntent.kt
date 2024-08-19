@file:Suppress("DEPRECATION")

package com.space.core_ui.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

inline fun <reified T : Any> AppCompatActivity.extra(
    key: String,
    default: T? = null
) = lazy {
    val value = intent.extras?.get(key)
    if (value is String) value else default
}

inline fun <reified T : Any> AppCompatActivity.extraNotNull(
    key: String,
    default: T? = null
) = lazy {
    val value = intent.extras?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}

@Suppress(names = ["UNCHECKED_CAST"])
fun <T : Serializable> Intent.intentSerializable(key: String, clazz: Class<T>): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.getSerializableExtra(key, clazz)
    } else {
        this.getSerializableExtra(key) as T?
    }
}

fun Context.startOpenBrowser(uri: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(this, "현재 사용중인 디바이스는 지원하지 않습니다.", Toast.LENGTH_LONG).show()
    }
}