package com.space.mileage.di

import com.space.navigator.NavigatorMileage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MileageNavigatorModule {
    @Binds
    abstract fun providerMileageNavigator(
        mileageNavigator: MileageNavigatorImpl
    ): NavigatorMileage

}