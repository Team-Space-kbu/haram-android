package com.space.mileage.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.mileage.MileageUseCase
import com.space.shared.data.mileage.MileageInfo
import com.space.shared.mapCatching
import com.space.shared.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MileageViewModel @Inject constructor(
    private val mileageUseCase: MileageUseCase
) : ViewModel() {

    private val _mileageInfo: MutableLiveData<MileageInfo> = MutableLiveData<MileageInfo>()
    val mileageInfo: LiveData<MileageInfo> = _mileageInfo

    init {
        viewModelScope.launch {
            val result = async { mileageUseCase() }.await()
            result.mapCatching(
                onSuccess = { mileageInfo ->
                    _mileageInfo.value = mileageInfo

                },
                onError = { throwable ->
                    Timber.i(throwable.message)
                }
            )
        }
    }
}