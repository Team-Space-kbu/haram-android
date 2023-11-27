package com.space.biblemon.util

import androidx.fragment.app.Fragment
import com.space.biblemon.ui.bible.BibleFragment
import com.space.biblemon.ui.book.info.BookDetailFragment
import com.space.biblemon.ui.book.search.BookSearchFragment
import com.space.biblemon.ui.intranet.chapel.ChapelFragment
import com.space.biblemon.ui.intranet.intranetLogin.IntranetFragment
import com.space.biblemon.ui.intranet.intranetInfo.IntranetInfoFragment
import com.space.biblemon.ui.notice.NoticeHomeFragment
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
        ViewType.INTRANET_CHAPEL to ChapelFragment::class.java,
        ViewType.INTRANET_LOGIN to IntranetFragment::class.java,
        ViewType.INTRANET_INFO to IntranetInfoFragment::class.java,
        ViewType.INTRANET_TIMETABLE to TimeTableFragment::class.java,
        ViewType.BOOK_HOME to null,
        ViewType.BOOK_DETAIL to BookDetailFragment::class.java,
        ViewType.BOOK_SEARCH to BookSearchFragment::class.java,
        ViewType.PARTNERS to PartnersFragment::class.java,
        ViewType.NOTICE to NoticeHomeFragment::class.java,
        ViewType.BIBLE to BibleFragment::class.java
    )

    fun createFragment(viewType: ViewType): Fragment? =
        fragmentMap[viewType]?.getConstructor()?.newInstance()

}
