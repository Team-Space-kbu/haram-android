package com.space.domain.bible

import com.space.domain.NonParamUseCase
import com.space.data.service.bible.BibleService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.bible.BibleInfo
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BibleInfoUseCase @Inject constructor(
    private val bibleService: BibleService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : NonParamUseCase<BibleInfo>(dispatcher) {
    override suspend fun execute(): BibleInfo {
        return bibleService.getBibleInfo()
    }
}