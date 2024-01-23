package com.space.mileage.di

import android.content.Context
import com.space.mileage.MileageActivity
import com.space.navigator.view.NavigatorMileage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MileageNavigatorModule {
    @Binds
    abstract fun providerMileageNavigator(
        mileageNavigator: MileageNavigatorImpl
    ): NavigatorMileage

}

internal class MileageNavigatorImpl @Inject constructor(): NavigatorMileage {
    override fun openView(context: Context) {
        MileageActivity.open(context)
    }
}