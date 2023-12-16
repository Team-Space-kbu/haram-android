package com.space.auth.di

import com.space.navigator.NavigatorLogin
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LoginNavigatorModule {
    @Binds
    abstract fun providerMainNavigator(
       loginNavigatorImpl: LoginNavigatorImpl
    ): NavigatorLogin

}