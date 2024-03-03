package com.space.board.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.board.BoardCategoryUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.board.BoardCategory
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val boardCategoryUseCase: BoardCategoryUseCase
) : ViewModel() {

    private val _category: MutableLiveData<UiStatus<List<BoardCategory>>> =
        MutableLiveData<UiStatus<List<BoardCategory>>>()
    val category: LiveData<UiStatus<List<BoardCategory>>> = _category

    init {
        viewModelScope.launch {
            val info = async { boardCategoryUseCase() }.await()
            info.mapCatching(
                onSuccess = { category ->
                    _category.value = UiStatus(UiStatusType.SUCCESS, category)
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                    when (throwable) {
                        is UnknownHostException -> {
                            _category.value = UiStatus(UiStatusType.NO_CONNECTION)
                        }

                        is SocketTimeoutException -> {
                            _category.value = UiStatus(UiStatusType.NO_CONNECTION)
                        }

                        is Exception -> {
                            _category.value = UiStatus(UiStatusType.ERROR)
                        }
                    }
                }
            )
        }
    }
}