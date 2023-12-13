package com.space.domain.usecase.bible

import com.space.domain.base.UseCase
import com.space.repository.service.inf.BibleService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.bible.BibleChapter
import com.space.shared.data.bible.BibleDetail
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BibleDetailUseCase @Inject constructor(
    private val bibleService: BibleService,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<BibleDetail, List<BibleChapter>>(dispatcher) {
    override suspend fun execute(param: BibleDetail): List<BibleChapter> {
        return bibleService.getBooks(param)
    }
}
