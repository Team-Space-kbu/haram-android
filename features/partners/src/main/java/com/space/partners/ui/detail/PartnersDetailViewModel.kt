package com.space.partners.ui.detail

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.partners.PartnersDetailUseCase
import com.space.navigator.view.NavigatorImage
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.partner.PartnersDetail
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartnersDetailViewModel @Inject constructor(
    private val partnersUseCase: PartnersDetailUseCase
) : BaseViewModel<PartnersDetail>() {

    @Inject
    lateinit var navigatorImage: NavigatorImage

    fun partnersDetail(id: String) {
        viewModelScope.launch {
            val info = async { partnersUseCase(id) }.await()
            info.mapCatching(
                onSuccess = {
                    _view.value = UiStatus(UiStatusType.SUCCESS, it)
                },
                onError = ::setIntranetData
            )
        }
    }

}