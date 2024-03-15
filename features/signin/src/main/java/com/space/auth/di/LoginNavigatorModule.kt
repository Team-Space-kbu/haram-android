package com.space.auth.di

import android.content.Context
import com.space.auth.ui.LoginActivity
import com.space.navigator.view.NavigatorLogin
import com.space.shared.data.auth.AuthType
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LoginNavigatorModule {
    @Binds
    abstract fun providerMainNavigator(
        impl: LoginNavigatorImpl
    ): NavigatorLogin
}

internal class LoginNavigatorImpl @Inject constructor(

) : NavigatorLogin {
    override fun openView(context: Context, authType: AuthType) {
        LoginActivity.open(context, authType)
    }

    override fun openView(context: Context) {
        LoginActivity.open(context, AuthType.LOGIN)
    }
}