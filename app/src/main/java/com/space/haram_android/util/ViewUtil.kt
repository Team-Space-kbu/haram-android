package com.space.haram_android.util

import androidx.fragment.app.Fragment
import com.space.haram_android.ui.book.home.BookHomeFragment
import com.space.haram_android.ui.book.info.BookDetailFragment
import com.space.haram_android.ui.book.search.BookSearchFragment
import com.space.haram_android.ui.bible.chapel.ChapelFragment
import com.space.haram_android.ui.bible.intranetLogin.IntranetFragment
import com.space.haram_android.ui.bible.intranetInfo.IntranetInfoFragment

enum class ViewType(val fragmentClass: Class<out Fragment>?) {
    NOT_THING(null),
    INTRANET_INFO(IntranetInfoFragment::class.java),
    INTRANET_LOGIN(IntranetFragment::class.java),
    INTRANET_CHAPEL(ChapelFragment::class.java),
    INTRANET_TIMETABLE(null),
    BOOK_HOME(BookHomeFragment::class.java),
    BOOK_DETAIL(BookDetailFragment::class.java),
    BOOK_SEARCH(BookSearchFragment::class.java)

}

object FragmentFactory {
    fun createFragment(viewType: ViewType): Fragment? {
        return when (viewType) {
            ViewType.INTRANET_CHAPEL -> ChapelFragment.newInstance()
            ViewType.INTRANET_LOGIN -> IntranetFragment.newInstance()
            ViewType.INTRANET_INFO -> IntranetInfoFragment.newInstance()
            ViewType.BOOK_HOME -> BookHomeFragment.newInstance()
            ViewType.BOOK_DETAIL -> BookDetailFragment.newInstance()
            ViewType.BOOK_SEARCH -> BookSearchFragment.newInstance()
            else -> {
                null
            }
        }
    }
}