package com.space.navigator.view

import android.content.Context
import com.space.navigator.BaseNavigator
import com.space.shared.type.SingupType

interface NavigatorSingup : BaseNavigator{
    fun openView(
        context: Context,
        type: SingupType
    )
}