package com.space.board.di

import com.space.navigator.NavigatorBoard
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BoardNavigatorModule {

    @Binds
    abstract fun providerBookNavigator(
        navigatorBoard: BoardNavigatorImpl
    ): NavigatorBoard

}