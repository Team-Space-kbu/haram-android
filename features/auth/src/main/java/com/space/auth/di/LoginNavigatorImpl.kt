package com.space.auth.di

import android.content.Context
import com.space.auth.ui.LoginActivity
import com.space.navigator.NavigatorLogin
import javax.inject.Inject

class LoginNavigatorImpl @Inject constructor() : NavigatorLogin {
    override fun openLogin(context: Context) {
        LoginActivity.open(context)
    }

}