package com.space.signin.di

import android.content.Context
import com.space.signin.LoginActivity
import com.space.navigator.view.NavigatorLogin
import com.space.shared.type.AuthType
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LoginNavigatorModule {
    @Binds
    abstract fun providerLoginNavigator(
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