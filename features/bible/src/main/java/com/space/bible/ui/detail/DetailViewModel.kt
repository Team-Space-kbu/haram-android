package com.space.bible.ui.detail

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.bible.BibleDetailUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.bible.BibleChapter
import com.space.shared.data.bible.BibleDetail
import com.space.shared.mapCatching
import com.space.shared.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val bibleDetailUseCase: BibleDetailUseCase,
) : BaseViewModel<List<BibleChapter>>() {

    fun setBible(bibleDetail: BibleDetail) {
        viewModelScope.launch {
            val result = async { bibleDetailUseCase(bibleDetail) }.await()
            result.mapCatching(
                onSuccess = {
                    _view.value = UiStatus(UiStatusType.SUCCESS, it)
                },
                onError = ::setIntranetData
            )
        }
    }
}