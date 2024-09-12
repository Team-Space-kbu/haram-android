package com.space.course

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.core_ui.extension.startActivity
import com.space.core_ui.extension.startFragment
import com.space.course.ui.home.CourseHomeFragment
import dagger.hilt.android.AndroidEntryPoint
import com.space.core_ui.R

@AndroidEntryPoint
class CourseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.startFragment<CourseHomeFragment>(
                R.id.container
            )
        }
    }

    companion object{
        fun open(context: Context){
            context.startActivity<CourseActivity>()
        }
    }
}