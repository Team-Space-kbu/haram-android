package com.space.bible.di

import com.space.navigator.NavigatorBible
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BibleNavigatorModule {

    @Binds
    abstract fun providerBibleNavigator(
        bibleNavigator: BibleNavigatorImpl
    ): NavigatorBible

}