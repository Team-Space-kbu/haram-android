package com.space.partners.di

import com.space.navigator.NavigatorPartners
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PartnersNavigatorModule {
    @Binds
    abstract fun providerPartnersNavigator(
        mileageNavigator: PartnersNavigatorImpl
    ): NavigatorPartners

}