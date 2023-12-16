package com.space.biblemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.space.domain.usecase.auth.AuthUseCase
import com.space.navigator.NavigatorLogin
import com.space.navigator.NavigatorMain
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HaramActivity : AppCompatActivity() {

    @Inject
    lateinit var loginUseCase: AuthUseCase

    @Inject
    lateinit var navigatorMain: NavigatorMain

    @Inject
    lateinit var navigatorLogin: NavigatorLogin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        if (loginUseCase.getToken() != null) {
            Timber.d("Access token valid!!")
            navigatorMain.openMain(this)
            finish()
        } else {
            Timber.d("Access token null!!")
            navigatorLogin.openLogin(this)
            finish()
        }
    }
}