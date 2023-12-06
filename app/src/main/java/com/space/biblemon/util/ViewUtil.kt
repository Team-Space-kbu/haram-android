package com.space.biblemon.util

import androidx.fragment.app.Fragment
import com.space.biblemon.ui.partners.PartnersFragment
import com.space.biblemon.ui.timetable.TimeTableFragment

enum class ViewType() {
    NOT_THING,
    INTRANET_INFO,
    INTRANET_LOGIN,
    INTRANET_CHAPEL,
    INTRANET_TIMETABLE,
    BOOK_HOME,
    BOOK_DETAIL,
    BOOK_SEARCH,
    PARTNERS,
    NOTICE,
    BIBLE,

}

object FragmentFactory {
    private val fragmentMap = mapOf(
        ViewType.INTRANET_TIMETABLE to TimeTableFragment::class.java,
        ViewType.BOOK_HOME to null,
        ViewType.PARTNERS to PartnersFragment::class.java,
    )

    fun createFragment(viewType: ViewType): Fragment? =
        fragmentMap[viewType]?.getConstructor()?.newInstance()

}
