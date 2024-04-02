package com.space.mileage.ui

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.mileage.MileageUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.mileage.MileageInfo
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MileageViewModel @Inject constructor(
    private val mileageUseCase: MileageUseCase
) : BaseViewModel<MileageInfo>() {


    init {
        viewModelScope.launch {
            val result = async { mileageUseCase() }.await()
            result.mapCatching(
                onSuccess = { mileageInfo ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, mileageInfo)
                },
                onError = ::setIntranetData
            )
        }
    }
}
