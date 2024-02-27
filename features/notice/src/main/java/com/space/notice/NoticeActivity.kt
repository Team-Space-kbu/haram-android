package com.space.notice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.core_ui.R
import com.space.core_ui.startActivity
import com.space.core_ui.startFragment
import com.space.notice.ui.home.NoticeHomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.startFragment<NoticeHomeFragment>(
                R.id.container
            )
        }
    }

    companion object {
        fun open(context: Context) {
            context.startActivity<NoticeActivity>()
        }
    }
}