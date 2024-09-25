package com.space.web_view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.core_ui.R
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.core_ui.extension.startActivity
import com.space.core_ui.extension.startFragment
import com.space.shared.encodeToString
import com.space.web_view.ui.WebViewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : AppCompatActivity() {

    private val webView by extraNotNull<String>("webView").map { it }

    companion object {
        fun open(context: Context, webView: Pair<String, String>) {
            context.startActivity<WebViewActivity>(
                "webView" to webView.encodeToString()
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.startFragment<WebViewFragment>(
                R.id.container,
                "webView" to webView
            )
        }
    }
}