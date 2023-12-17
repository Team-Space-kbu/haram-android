package com.space.auth

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import com.space.auth.ui.login.LoginFragment
import com.space.core_ui.startActivity
import dagger.hilt.android.AndroidEntryPoint
import com.space.core_ui.R

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(R.id.container, LoginFragment.newInstance())
            }
        }
    }

    companion object {
        fun open(context: Context) {
            context.startActivity<AuthActivity>()
        }
    }
}