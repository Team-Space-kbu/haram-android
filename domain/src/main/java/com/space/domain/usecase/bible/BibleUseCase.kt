package com.space.domain.usecase.bible

import com.space.data.service.bible.BibleService
import javax.inject.Inject

class BibleUseCase @Inject constructor(
    private val bibleService: BibleService,
) {
    fun execute(): Map<String, Int> {
        return bibleService.getBible()
    }
}