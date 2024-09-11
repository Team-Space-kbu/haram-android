package com.space.class_room.di

import android.content.Context
import com.space.class_room.ClassRoomActivity
import com.space.navigator.view.NavigatorClassRoom
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigatorClassRoomModule {
    @Binds
    abstract fun providerClassRoomNavigator(
        navigatorClassroomImpl: NavigatorClassRoomImpl
    ): NavigatorClassRoom

}

internal class NavigatorClassRoomImpl @Inject constructor(

) : NavigatorClassRoom {
    override fun openView(context: Context) {
        ClassRoomActivity.open(context)
    }
}