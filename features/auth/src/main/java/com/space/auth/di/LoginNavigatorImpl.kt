package com.space.auth.di

import android.content.Context
import com.space.auth.AuthActivity
import com.space.navigator.NavigatorLogin
import javax.inject.Inject

class LoginNavigatorImpl @Inject constructor() : NavigatorLogin {
    override fun openLogin(context: Context) {
        AuthActivity.open(context)
    }

}