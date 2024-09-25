package com.space.navigator.view

import android.content.Context

interface NavigatorWebView {
    fun openView(
        context: Context,
        webView: Pair<String, String>
    )
}