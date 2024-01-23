package com.space.chapel.di

import android.content.Context
import com.space.chapel.ChapelActivity
import com.space.navigator.view.NavigatorChapel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigatorChapelModule {
    @Binds
    abstract fun providerMileageNavigator(
        navigatorChapelImpl: NavigatorChapelImpl
    ): NavigatorChapel

}

internal class NavigatorChapelImpl @Inject constructor(): NavigatorChapel {
    override fun openView(context: Context) {
        ChapelActivity.open(context)
    }
}