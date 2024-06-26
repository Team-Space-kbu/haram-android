package com.space.navigator.view

import android.content.Context
import com.space.shared.data.home.Notice
import com.space.shared.data.notice_space.SpaceNoticeData

interface NavigatorNoticeSpace{
    fun openView(
        context: Context,
        type: SpaceNoticeData
    )

    fun openView(
        context: Context,
        type: SpaceNoticeData,
        data: Notice
    )
}