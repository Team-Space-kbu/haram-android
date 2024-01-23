package com.space.book.di

import android.content.Context
import com.space.book.BookActivity
import com.space.navigator.view.NavigatorBook
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BookNavigatorModule {

    @Binds
    abstract fun providerBookNavigator(
        bookNavigator: BookNavigatorImpl
    ): NavigatorBook

}

internal class BookNavigatorImpl @Inject constructor() : NavigatorBook {
    override fun openView(context: Context) {
        BookActivity.open(context)
    }
}