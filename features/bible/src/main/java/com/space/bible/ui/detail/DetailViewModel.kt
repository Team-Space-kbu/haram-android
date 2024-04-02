package com.space.bible.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.bible.BibleDetailUseCase
import com.space.shared.data.bible.BibleChapter
import com.space.shared.data.bible.BibleDetail
import com.space.shared.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val bibleDetailUseCase: BibleDetailUseCase,
): ViewModel() {
    private val _bibleInfo: MutableLiveData<List<BibleChapter>> = MutableLiveData<List<BibleChapter>>()
    val bibleInfo: LiveData<List<BibleChapter>> = _bibleInfo


    fun setBible(bibleDetail: BibleDetail){
        viewModelScope.launch {
            val result = async { bibleDetailUseCase(bibleDetail) }
            _bibleInfo.value = result.await().succeeded()
        }
    }
}