package com.space.chapel.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.chapel.ChapelDetailUseCase
import com.space.domain.usecase.chapel.ChapelInfoUseCase
import com.space.shared.data.chapel.Chapel
import com.space.shared.result.succeeded
import com.space.shared.result.successOr
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChapelViewModel @Inject constructor(
    private val chapelInfoUseCase: ChapelInfoUseCase,
    private val chapelDetailUseCase: ChapelDetailUseCase
) : ViewModel() {

    private val _chapelInfo: MutableLiveData<Chapel> = MutableLiveData<Chapel>()
    val chapelInfo: LiveData<Chapel> = _chapelInfo

    init {
        viewModelScope.launch {
            val info = async { chapelInfoUseCase() }
            val detail = async { chapelDetailUseCase() }
            _chapelInfo.value = Chapel(
                chapelInfo = info.await().succeeded()!!,
                chapelDetail = detail.await().successOr(emptyList())
            )
        }
    }
}