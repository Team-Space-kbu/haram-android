package com.space.auth.ui.intranet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.intranet.IntranetUseCase
import com.space.shared.SpaceBody
import com.space.shared.UiStatusType
import com.space.shared.common.exception.NotMatchIntranet
import com.space.shared.mapCatching
import com.space.shared.model.IntranetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class IntranetViewModel @Inject constructor(
    private val intranetUseCase: IntranetUseCase
) : ViewModel() {

    private val _liveData: MutableLiveData<UiStatusType> = MutableLiveData<UiStatusType>()
    val liveData: LiveData<UiStatusType> = _liveData


    fun setIntranet(intranetModel: IntranetModel) {
        viewModelScope.launch {
            val result = async { intranetUseCase(intranetModel) }.await()
            result.mapCatching(
                onSuccess = {
                    _liveData.value = UiStatusType.SUCCESS
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                    when (throwable) {
                        is NotMatchIntranet -> {
                            _liveData.value = UiStatusType.REJECT
                        }

                        is Exception -> {
                            _liveData.value = UiStatusType.ERROR
                        }
                    }

                }
            )
        }
    }
}