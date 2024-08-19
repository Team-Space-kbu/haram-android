package com.space.rothem

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.core_ui.R
import com.space.core_ui.extension.startActivity
import com.space.core_ui.extension.startFragment
import com.space.rothem.ui.home.RothemFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RothemActivity : AppCompatActivity() {

    companion object {
        fun open(context: Context) {
            context.startActivity<RothemActivity>()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.startFragment<RothemFragment>(
                R.id.container
            )
        }
    }
}