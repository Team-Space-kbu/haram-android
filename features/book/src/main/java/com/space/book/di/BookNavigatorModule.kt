package com.space.book.di

import com.space.book.navigate.BookNavigatorImpl
import com.space.navigator.NavigatorBook
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BookNavigatorModule {

    @Binds
    abstract fun providerBookNavigator(
        bookNavigator: BookNavigatorImpl
    ): NavigatorBook

}