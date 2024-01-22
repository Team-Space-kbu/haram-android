package com.space.rothem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.rothem.ui.main.RothemFragment

class RothemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rothem)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RothemFragment.newInstance())
                .commitNow()
        }
    }
}