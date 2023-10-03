package com.space.domain.usecase

import com.space.domain.service.BibleService
import com.space.repository.bible.BibleRepository
import javax.inject.Inject

class BibleUseCase @Inject constructor(
    private val bibleRepository: BibleRepository,
    private val bibleService: BibleService
) {

    fun getBibleData(): LinkedHashMap<String, Int> {
        return bibleRepository.getBibleData()
    }

    fun getBibleInfo(){

    }

    fun getChapterList(){

    }
}