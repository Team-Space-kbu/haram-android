package com.space.image.di

import android.content.Context
import com.space.image.ImageActivity
import com.space.navigator.view.NavigatorChapel
import com.space.navigator.view.NavigatorImage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigatorImageModule {
    @Binds
    abstract fun providerImageNavigator(
        impl: NavigatorImageImpl
    ): NavigatorImage

}

internal class NavigatorImageImpl @Inject constructor() : NavigatorImage {
    override fun openView(context: Context, image: String) {
        ImageActivity.open(context, image)
    }
}