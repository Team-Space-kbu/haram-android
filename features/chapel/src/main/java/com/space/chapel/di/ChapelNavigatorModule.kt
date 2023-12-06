package com.space.chapel.di

import com.space.chapel.navigate.NavigatorChapelImpl
import com.space.navigator.NavigatorChapel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal abstract class ChapelNavigatorModule {
    @Binds
    abstract fun providerMileageNavigator(
        navigatorChapelImpl: NavigatorChapelImpl
    ): NavigatorChapel

}