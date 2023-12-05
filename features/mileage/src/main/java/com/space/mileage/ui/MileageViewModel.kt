package com.space.mileage.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.mileage.MileageUseCase
import com.space.shared.data.mileage.MileageInfo
import com.space.shared.result.succeeded
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MileageViewModel @Inject constructor(
    private val mileageUseCase: MileageUseCase
) : ViewModel() {

    private val _mileageInfo: MutableLiveData<MileageInfo> = MutableLiveData<MileageInfo>()
    val mileageInfo: LiveData<MileageInfo> = _mileageInfo

    init {
        viewModelScope.launch {
            val result = async { mileageUseCase() }
            _mileageInfo.value = result.await().succeeded()
        }
    }
}