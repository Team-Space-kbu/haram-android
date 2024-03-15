package com.space.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.core_ui.R
import com.space.core_ui.startFragment
import com.space.signup.ui.main.SignupFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.startFragment<SignupFragment>(
                R.id.container
            )
        }
    }
}