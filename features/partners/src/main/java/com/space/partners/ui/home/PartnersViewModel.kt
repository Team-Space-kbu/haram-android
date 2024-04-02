package com.space.partners.ui.home

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.partners.PartnersUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.partner.Partner
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartnersViewModel @Inject constructor(
    private val partnersUseCase: PartnersUseCase
) : BaseViewModel<List<Partner>>() {
    init {
        viewModelScope.launch {
            val info = async { partnersUseCase() }.await()
            info.mapCatching(
                onSuccess = { partners ->
                    _view.value = UiStatus(
                        UiStatusType.SUCCESS,
                        partners
                    )
                },
                onError = ::setIntranetData
            )
        }
    }

}