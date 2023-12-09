package com.space.chapel.di

import android.content.Context
import com.space.chapel.ChapelActivity
import com.space.navigator.NavigatorChapel
import com.space.navigator.NavigatorMileage
import javax.inject.Inject

class NavigatorChapelImpl @Inject constructor(): NavigatorChapel {
    override fun openChapelInfo(context: Context) {
        ChapelActivity.open(context)
    }
}
