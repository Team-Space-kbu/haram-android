package com.space.class_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.class_room.ui.ClassRoomFragment

class ClassRoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_room)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ClassRoomFragment.newInstance())
                .commitNow()
        }
    }
}