package com.space.book.navigate

import android.content.Context
import com.space.book.BookActivity
import com.space.navigator.NavigatorBook
import javax.inject.Inject


internal class BookNavigatorImpl @Inject constructor() : NavigatorBook {
    override fun openBookInfo(context: Context) {
        BookActivity.open(context)
    }
}