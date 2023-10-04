package com.space.biblemon.ui.bible

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.space.biblemon.base.view.BaseViewModel
import com.space.domain.usecase.BibleUseCase
import javax.inject.Inject

class BibleViewModel @Inject constructor(
    private val bibleUseCase: BibleUseCase
): BaseViewModel() {
    private val _bibleChapter: MutableLiveData<LinkedHashMap<String, Int>?> = MutableLiveData<LinkedHashMap<String, Int>?>()
    val bibleChapter: LiveData<LinkedHashMap<String, Int>?> = _bibleChapter

    init {
        _bibleChapter.value = bibleUseCase.getBibleData()
    }


}