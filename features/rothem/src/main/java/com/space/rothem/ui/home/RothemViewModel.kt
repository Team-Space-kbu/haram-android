package com.space.rothem.ui.home

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.rothem.RothemHome
import com.space.navigator.view.NavigatorNoticeSpace
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.rothem.Rothem
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RothemViewModel @Inject constructor(
    private val rothemHome: RothemHome
) : BaseViewModel<Rothem>() {
    
    @Inject
    lateinit var navigatorNoticeSpace: NavigatorNoticeSpace


    init {
        getRothemHome()
    }

    fun getRothemHome(){
        viewModelScope.launch {
            val result = async { rothemHome() }.await()
            result.mapCatching(
                onSuccess = { rothem ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, rothem)
                },
                onError = ::setIntranetData
            )
        }
    }
}