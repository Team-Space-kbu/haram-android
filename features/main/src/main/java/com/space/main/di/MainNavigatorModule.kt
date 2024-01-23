package com.space.main.di

import android.content.Context
import com.space.main.MainActivity
import com.space.navigator.view.NavigatorMain
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MainNavigatorModule {
    @Binds
    @Singleton
    abstract fun providerMainNavigator(
        mainNavigatorImpl: MainNavigatorImpl
    ): NavigatorMain
}

internal class MainNavigatorImpl @Inject constructor() : NavigatorMain {
    override fun openView(context: Context) {
        MainActivity.open(context)
    }
}