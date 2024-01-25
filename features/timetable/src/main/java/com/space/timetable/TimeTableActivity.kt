package com.space.timetable

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.core_ui.R
import com.space.core_ui.startActivity
import com.space.core_ui.startFragment
import com.space.timetable.ui.TimeTableFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimeTableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.startFragment<TimeTableFragment>(
                R.id.container
            )
        }
    }
    companion object {
        fun open(context: Context) {
            context.startActivity<TimeTableActivity>()
        }
    }
}