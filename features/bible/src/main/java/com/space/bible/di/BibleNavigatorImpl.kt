package com.space.bible.di

import android.content.Context
import com.space.bible.BibleActivity
import com.space.navigator.NavigatorBible
import javax.inject.Inject


internal class BibleNavigatorImpl @Inject constructor() : NavigatorBible {
    override fun openBible(context: Context) {
        BibleActivity.open(context)
    }
}