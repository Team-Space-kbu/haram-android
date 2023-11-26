package com.space.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.home.Home
import data.home.Shortcut
import result.succeeded
import result.successOr
import com.space.domain.usecase.HomeUseCase
import com.space.domain.usecase.ShortcutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val shortUseCase: ShortcutUseCase,
) : ViewModel() {

    private val _homeInfo = MutableLiveData<Home>()
    val homeInfo: LiveData<Home> = _homeInfo

    private val _shortcut = MutableLiveData<List<Shortcut>>()
    val shortcut: LiveData<List<Shortcut>> = _shortcut


    init {
        viewModelScope.launch {
            val homeInfo = async { homeUseCase() }.await().succeeded()
            val shortcut = async { shortUseCase() }.await().successOr(emptyList())
            _homeInfo.value = Home(
                notice = homeInfo.notice.notices.ifEmpty { emptyList() },
                kokkos = homeInfo.kokkoks.kokkoksNews.ifEmpty { emptyList() },
                slider = homeInfo.banner.banners.ifEmpty { emptyList() }
            )
            _shortcut.value = shortcut
        }
    }


}