package com.space.timetable.di

import android.content.Context
import com.space.navigator.view.NavigatorTimetable
import com.space.timetable.TimeTableActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TimetableNavigatorModule {
    @Binds
    abstract fun providerTimetableNavigator(
        impl: TimetableNavigatorImpl
    ): NavigatorTimetable

}

internal class TimetableNavigatorImpl @Inject constructor(): NavigatorTimetable {
    override fun openView(context: Context) {
        TimeTableActivity.open(context)
    }
}