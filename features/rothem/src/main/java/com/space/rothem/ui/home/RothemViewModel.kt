package com.space.rothem.ui.home

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.rothem.RothemHomeUseCase
import com.space.navigator.view.NavigatorNoticeSpace
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.rothem.RothemHome
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RothemViewModel @Inject constructor(
    private val rothemHomeUseCase: RothemHomeUseCase
) : BaseViewModel<RothemHome>() {
    
    @Inject
    lateinit var navigatorNoticeSpace: NavigatorNoticeSpace


    init {
        getRothemHome()
    }

    fun getRothemHome(){
        viewModelScope.launch {
            val result = async { rothemHomeUseCase() }.await()
            result.mapCatching(
                onSuccess = { rothem ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, rothem)
                },
                onError = ::setIntranetData
            )
        }
    }
}