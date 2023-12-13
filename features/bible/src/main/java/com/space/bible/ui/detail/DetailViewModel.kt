package com.space.bible.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.space.domain.usecase.bible.BibleDetailUseCase
import com.space.shared.data.bible.BibleInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val bibleDetailUseCase: BibleDetailUseCase,
): ViewModel() {
    private val _bibleInfo: MutableLiveData<BibleInfo> = MutableLiveData<BibleInfo>()
    val bibleInfo: LiveData<BibleInfo> = _bibleInfo


}