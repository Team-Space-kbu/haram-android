package com.space.navigator.view

import android.content.Context

interface NavigatorPdf {
    fun openView(
        context: Context,
        pdf: String
    )
}