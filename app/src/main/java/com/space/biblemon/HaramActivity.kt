package com.space.biblemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.space.navigator.view.NavigatorLogin
import com.space.navigator.view.NavigatorMain
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HaramActivity : AppCompatActivity() {

    private val viewModel: HaramViewModel by viewModels()

    @Inject
    lateinit var navigatorLogin: NavigatorLogin

    @Inject
    lateinit var navigatorMain: NavigatorMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        viewModel.loginState.observe(this) {
            when (it) {
                true -> {
                    navigatorMain.openView(this)
                    finish()
                }

                false -> {
                    navigatorLogin.openView(this)
                    finish()
                }
            }
        }

    }
}