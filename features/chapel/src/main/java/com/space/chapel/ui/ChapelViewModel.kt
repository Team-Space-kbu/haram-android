package com.space.chapel.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.chapel.ChapelUseCase
import com.space.shared.data.chapel.Chapel
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChapelViewModel @Inject constructor(
    private val chapelUseCase: ChapelUseCase
) : ViewModel() {

    private val _chapelInfo: MutableLiveData<Chapel> = MutableLiveData<Chapel>()
    val chapelInfo: LiveData<Chapel> = _chapelInfo

    init {
        viewModelScope.launch {
            val info = async { chapelUseCase() }.await()
            info.mapCatching(
                onSuccess = { chapel ->
                    _chapelInfo.value = chapel
                },
                onError = { error ->
                    Timber.d(error.message)
                }
            )
        }
    }
}