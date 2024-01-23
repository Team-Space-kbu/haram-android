package com.space.rothem.di

import android.content.Context
import com.space.navigator.view.NavigatorRothem
import com.space.rothem.RothemActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PartnersNavigatorModule {
    @Binds
    abstract fun providerPartnersNavigator(
        mileageNavigator: PartnersNavigatorImpl
    ): NavigatorRothem

}

internal class PartnersNavigatorImpl @Inject constructor() : NavigatorRothem {
    override fun openView(context: Context) {
        RothemActivity.open(context)
    }
}