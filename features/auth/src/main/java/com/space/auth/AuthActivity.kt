package com.space.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import com.space.auth.ui.LoginFragment

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(R.id.container, LoginFragment.newInstance())
            }
        }
    }
}