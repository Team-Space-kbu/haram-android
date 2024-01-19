package com.space.auth.di

import android.content.Context
import com.space.auth.ui.LoginActivity
import com.space.navigator.NavigatorLogin
import com.space.shared.data.auth.AuthType
import javax.inject.Inject

class LoginNavigatorImpl @Inject constructor() : NavigatorLogin {
    override fun openLogin(context: Context) {
        LoginActivity.open(context, AuthType.LOGIN)
    }

}