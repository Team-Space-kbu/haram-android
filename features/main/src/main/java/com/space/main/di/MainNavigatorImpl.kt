package com.space.main.di

import android.content.Context
import com.space.main.MainActivity
import com.space.navigator.NavigatorMain
import javax.inject.Inject

class MainNavigatorImpl @Inject constructor() : NavigatorMain {
    override fun openMain(context: Context) {
        MainActivity.open(context)
    }

}