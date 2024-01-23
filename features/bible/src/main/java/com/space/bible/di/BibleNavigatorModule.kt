package com.space.bible.di

import android.content.Context
import com.space.bible.BibleActivity
import com.space.navigator.view.NavigatorBible
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BibleNavigatorModule {

    @Binds
    abstract fun providerBibleNavigator(
        bibleNavigator: BibleNavigatorImpl
    ): NavigatorBible

}

internal class BibleNavigatorImpl @Inject constructor() : NavigatorBible {
    override fun openView(context: Context) {
        BibleActivity.open(context)
    }
}