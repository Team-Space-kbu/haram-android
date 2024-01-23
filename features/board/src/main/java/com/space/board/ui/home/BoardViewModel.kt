package com.space.board.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.board.BoardCategoryUseCase
import com.space.shared.data.board.BoardCategory
import com.space.shared.result.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val boardCategoryUseCase: BoardCategoryUseCase
) : ViewModel() {

    private val _category: MutableLiveData<List<BoardCategory>> = MutableLiveData<List<BoardCategory>>()
    val category: LiveData<List<BoardCategory>> = _category

    init {
        viewModelScope.launch {
            val info = async { boardCategoryUseCase() }.await()
            info.mapCatching(
                onSuccess = {
                    _category.value = it
                },
                onError = {
                    _category.value = emptyList()
                }
            )
        }
    }
}