package com.space.chapel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.core_ui.R
import com.space.chapel.ui.ChapelFragment
import com.space.core_ui.extension.startActivity
import com.space.core_ui.extension.startFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChapelActivity : AppCompatActivity() {
    companion object {
        fun open(context: Context) {
            context.startActivity<ChapelActivity>()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.startFragment<ChapelFragment>(
                R.id.container
            )
        }
    }
}