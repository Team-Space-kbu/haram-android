package com.space.bible.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.bible.BibleInfoUseCase
import com.space.domain.bible.BibleUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.bible.BibleDetail
import com.space.shared.data.bible.BibleInfo
import com.space.shared.data.bible.SelectorBible
import com.space.shared.mapCatching
import com.space.shared.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class BibleViewModel @Inject constructor(
    private val bibleInfoUseCase: BibleInfoUseCase,
    private val bibleUseCase: BibleUseCase
) : BaseViewModel<BibleInfo>() {


    private val _chapter: MutableLiveData<String> = MutableLiveData<String>("창세기")
    val chapter: LiveData<String> = _chapter

    private val _verse: MutableLiveData<Int> = MutableLiveData<Int>(1)
    val verse: LiveData<Int> = _verse


    val bible: Map<String, Int> = bibleUseCase.execute()

    init {
        viewModelScope.launch {
            val bibleInfo = async { bibleInfoUseCase() }
            bibleInfo.await().mapCatching(
                onSuccess = { info ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, info)
                },
                onError = ::setIntranetData
            )
        }
    }

    fun setChapter(chapter: String) {
        _chapter.value = chapter
        _verse.value = 1
    }

    fun setVerse(verse: Int) {
        _verse.value = verse
    }

    fun getBibleDetail(): BibleDetail =
        BibleDetail(
            chapter = chapter.value.toString(),
            verse = verse.value.toString()
        )

    fun setSelectChapter() =
        SelectorBible(status = false, bible.keys.toList())


    fun setSelectVerse() =
        SelectorBible(status = true, verse = bible[chapter.value]!!.toInt())


}