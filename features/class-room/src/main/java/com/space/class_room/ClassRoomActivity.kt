package com.space.class_room

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.class_room.ui.home.ClassRoomFragment
import com.space.core_ui.R
import com.space.core_ui.extension.startActivity
import com.space.core_ui.extension.startFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassRoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.startFragment<ClassRoomFragment>(
                R.id.container
            )
        }
    }

    companion object{
        fun open(context: Context){
            context.startActivity<ClassRoomActivity>()
        }
    }

}