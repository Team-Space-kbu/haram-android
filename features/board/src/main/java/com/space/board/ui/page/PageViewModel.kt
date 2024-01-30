package com.space.board.ui.page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.board.BoardPageUseCase
import com.space.shared.data.board.BoardPage
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor(
    private val boardPageUseCase: BoardPageUseCase
) : ViewModel() {

    private val _category: MutableLiveData<List<BoardPage>> = MutableLiveData<List<BoardPage>>()
    val category: LiveData<List<BoardPage>> = _category

    fun getPages(type: String) {
        viewModelScope.launch {
            val page = async { boardPageUseCase(type) }.await()
            page.mapCatching(
                onSuccess = { boardPage ->
                    _category.value = boardPage
                },
                onError = { error ->
                    Timber.d(error.message)
                    _category.value = emptyList()
                }
            )
        }
    }
}