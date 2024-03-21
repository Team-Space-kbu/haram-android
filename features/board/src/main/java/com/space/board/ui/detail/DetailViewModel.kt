package com.space.board.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.board.BoardDetailUseCase
import com.space.shared.data.board.BoardDetail
import com.space.shared.data.board.BoardDetailNum
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val boardDetailUseCase: BoardDetailUseCase
) : ViewModel() {

    private val _detail: MutableLiveData<BoardDetail> = MutableLiveData<BoardDetail>()
    val detail: LiveData<BoardDetail> = _detail

    fun getDetail(detailNum: BoardDetailNum) {
        viewModelScope.launch {
            val detail = async { boardDetailUseCase(detailNum) }.await()
            detail.mapCatching(
                onSuccess = { boardDetail ->
                    _detail.value = boardDetail
                },
                onError = { error ->
                    Timber.d(error.message)
                }
            )
        }
    }
}