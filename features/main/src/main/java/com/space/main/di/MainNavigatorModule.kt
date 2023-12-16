package com.space.main.di

import com.space.navigator.NavigatorMain
import com.space.navigator.NavigatorMileage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MainNavigatorModule {
    @Binds
    abstract fun providerMainNavigator(
        mainNavigatorImpl: MainNavigatorImpl
    ): NavigatorMain

}