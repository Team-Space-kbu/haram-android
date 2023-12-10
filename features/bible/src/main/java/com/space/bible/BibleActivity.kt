package com.space.bible

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import com.space.bible.ui.BibleFragment
import com.space.core_ui.startActivity
import dagger.hilt.android.AndroidEntryPoint
import com.space.core_ui.R

@AndroidEntryPoint
internal class BibleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(R.id.container, BibleFragment.newInstance())
            }
        }
    }

    companion object {
        fun open(context: Context) {
            context.startActivity<BibleActivity>()
        }
    }
}