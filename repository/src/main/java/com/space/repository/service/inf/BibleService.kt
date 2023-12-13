package com.space.repository.service.inf

import com.space.shared.data.bible.BibleChapter
import com.space.shared.data.bible.BibleDetail
import com.space.shared.data.bible.BibleInfo

interface BibleService {
    suspend fun getBibleInfo(): BibleInfo
    suspend fun getBooks(bibleDetail: BibleDetail): List<BibleChapter>

    fun getBible(): Map<String, Int>

}