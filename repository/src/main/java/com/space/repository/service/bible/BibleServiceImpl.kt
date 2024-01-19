package com.space.repository.service.bible

import com.space.repository.api.BibleApi
import com.space.repository.repository.BibleRepository
import com.space.shared.data.bible.BibleChapter
import com.space.shared.data.bible.BibleDetail
import com.space.shared.data.bible.BibleInfo
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class BibleServiceImpl @Inject constructor(
    private val bibleApi: BibleApi,
    private val bibleRepository: BibleRepository
) : BibleService {
    override suspend fun getBibleInfo(): BibleInfo {
        return runBlocking {
            bibleApi.getBible().data
        }
    }


    override suspend fun getBooks(bibleDetail: BibleDetail): List<BibleChapter> {
        return runBlocking {
            bibleApi.getBooks(
                book = bibleDetail.chapter,
                chapter = bibleDetail.verse
            ).data
        }
    }

    override fun getBible(): Map<String, Int> {
        return runBlocking {
            bibleRepository.getBibleData()
        }
    }

}