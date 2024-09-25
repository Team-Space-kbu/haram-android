package com.space.pdf_viewer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.pdf_viewer.ui.ViewerFragment
import dagger.hilt.android.AndroidEntryPoint
import com.space.core_ui.R
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.core_ui.extension.startActivity
import com.space.core_ui.extension.startFragment

@AndroidEntryPoint
class ViewerActivity : AppCompatActivity() {
    private val pdf by extraNotNull<String>("pdf").map { it }

    companion object {
        fun open(context: Context, pdf: String) {
            context.startActivity<ViewerActivity>(
                "pdf" to pdf
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.startFragment<ViewerFragment>(
                R.id.container,
                "pdf" to pdf
            )
        }
    }
}
