package com.space.navigator.view

import android.content.Context
import com.space.navigator.BaseNavigator
import com.space.shared.type.AuthType

interface NavigatorLogin : BaseNavigator{
    fun openView(
        context: Context,
        authType: AuthType
    )
}
