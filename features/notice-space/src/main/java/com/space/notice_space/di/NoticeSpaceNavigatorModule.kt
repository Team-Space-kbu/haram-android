package com.space.notice_space.di

import android.content.Context
import com.space.navigator.view.NavigatorNoticeSpace
import com.space.notice_space.NoticeSpaceActivity
import com.space.shared.data.notice_space.SpaceNoticeData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SpaceNoticeModule {
    @Binds
    abstract fun providerNoticeNavigator(
        impl: NavigatorNoticeSpaceImpl
    ): NavigatorNoticeSpace

}

internal class NavigatorNoticeSpaceImpl @Inject constructor(
) : NavigatorNoticeSpace {
    override fun openView(context: Context, type: SpaceNoticeData) {
        NoticeSpaceActivity.open(context, type)
    }
}