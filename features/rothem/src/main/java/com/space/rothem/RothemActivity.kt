package com.space.rothem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.core_ui.startFragment
import com.space.rothem.ui.RothemFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RothemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rothem)
        if (savedInstanceState == null) {
            supportFragmentManager.startFragment<RothemFragment>(
                R.id.container
            )
        }
    }
}