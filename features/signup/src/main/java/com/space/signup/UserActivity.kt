package com.space.signup

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.signup.ui.user.UserFragment
import com.space.core_ui.R
import com.space.core_ui.startActivity
import com.space.core_ui.startFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.startFragment<UserFragment>(
                R.id.container
            )
        }
    }

    companion object {
        fun open(context: Context) {
            context.startActivity<UserActivity>()
        }
    }
}