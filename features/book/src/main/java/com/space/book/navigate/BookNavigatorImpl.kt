package com.space.book.navigate

import android.content.Context
import com.space.book.BookActivity
import com.space.navigator.NavigatorBookInfo
import javax.inject.Inject


internal class BookNavigatorImpl @Inject constructor(

) : NavigatorBookInfo {
    override fun openBookInfo(context: Context) {
        BookActivity.open(context)
    }
}