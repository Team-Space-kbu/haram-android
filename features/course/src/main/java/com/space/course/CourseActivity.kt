package com.space.course

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.course.ui.CourseFragment

class CourseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CourseFragment.newInstance())
                .commitNow()
        }
    }
}