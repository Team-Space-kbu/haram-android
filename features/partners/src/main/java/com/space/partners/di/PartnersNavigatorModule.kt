package com.space.partners.di

import android.content.Context
import com.space.navigator.view.NavigatorPartners
import com.space.partners.PartnersActivity
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
    ): NavigatorPartners

}

internal class PartnersNavigatorImpl @Inject constructor(): NavigatorPartners {
    override fun openView(context: Context) {
        PartnersActivity.open(context)
    }
}