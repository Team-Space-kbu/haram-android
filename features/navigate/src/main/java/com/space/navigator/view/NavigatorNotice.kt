package com.space.navigator.view

import android.content.Context
import com.space.navigator.BaseNavigator
import com.space.shared.data.notice_bible.NoticeViewType

interface NavigatorNotice : BaseNavigator {
    fun openView(
        context: Context,
        noticeViewType: NoticeViewType
    )
}