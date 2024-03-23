package com.space.notice.di

import android.content.Context
import com.space.navigator.view.NavigatorNotice
import com.space.notice.NoticeActivity
import com.space.shared.data.notice.NoticeViewType
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PartnersNoticeModule {
    @Binds
    abstract fun providerNoticeNavigator(
        mileageNavigator: NavigatorNoticeImpl
    ): NavigatorNotice

}

internal class NavigatorNoticeImpl @Inject constructor(

) : NavigatorNotice {
    override fun openView(context: Context, noticeViewType: NoticeViewType) {
        NoticeActivity.open(context, noticeViewType)
    }

    override fun openView(context: Context) {
        NoticeActivity.open(context, NoticeViewType.DEFAULT)
    }
}