package com.space.biblemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HaramActivity : AppCompatActivity() {

    private val viewModel: HaramViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        viewModel.loginState.observe(this) {
            when (it) {
                true -> {
                    viewModel.navigatorMain.openMain(this)
                    finish()
                }

                false -> {
                    viewModel.navigatorLogin.openLogin(this)
                    finish()
                }
            }
        }

    }
}