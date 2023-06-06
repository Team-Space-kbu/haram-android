package com.space.haram_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.haram_android.R
import com.space.haram_android.ui.home.HomeFragment
import com.space.haram_android.ui.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
    }
}