package com.space.book.di

import android.content.Context
import com.space.book.BookActivity
import com.space.navigator.NavigatorBook
import javax.inject.Inject


internal class BookNavigatorImpl @Inject constructor() : NavigatorBook {
    override fun openBookInfo(context: Context) {
        BookActivity.open(context)
    }
}