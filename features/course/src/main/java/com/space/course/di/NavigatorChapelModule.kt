package com.space.course.di

import android.content.Context
import com.space.course.CourseActivity
import com.space.navigator.view.NavigatorClassRoom
import com.space.navigator.view.NavigatorCourse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigatorCourseModule {

    @Binds
    abstract fun providerCourseNavigator(
        navigatorCourseImpl: NavigatorCourseRoomImpl
    ): NavigatorCourse

}

internal class NavigatorCourseRoomImpl @Inject constructor(

) : NavigatorCourse {
    override fun openView(context: Context) {
        CourseActivity.open(context)
    }
}