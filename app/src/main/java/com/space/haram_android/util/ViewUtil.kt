package com.space.haram_android.util

import androidx.fragment.app.Fragment
import com.space.haram_android.ui.book.home.BookHomeFragment
import com.space.haram_android.ui.book.info.BookDetailFragment
import com.space.haram_android.ui.intranet.IntranetFragment

enum class ViewType(val value: Int, val fragmentClass: Class<out Fragment>?) {
    NOT_THING(99, null),
    INTRANET_CHAPEL(0, IntranetFragment::class.java),
    INTRANET_TIMETABLE(1, null),
    BOOK_HOME(2, BookHomeFragment::class.java),
    BOOK_DETAIL(3, BookDetailFragment::class.java)

}

object FragmentFactory {
    fun createFragment(viewType: ViewType): Fragment? {
        return when (viewType) {
            ViewType.INTRANET_CHAPEL -> IntranetFragment.newInstance()
            ViewType.BOOK_HOME -> BookHomeFragment.newInstance()
            ViewType.BOOK_DETAIL -> BookDetailFragment.newInstance()
            else -> {
                null
            }
        }
    }
}