package com.space.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.data.data.home.Home
import com.space.data.result.succeeded
import com.space.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _homeInfo = MutableLiveData<Home>()
    val homeInfo: LiveData<Home> = _homeInfo

    init {
        viewModelScope.launch {
            val homeInfo = async { homeUseCase() }.await().succeeded()
            _homeInfo.value = Home(
                notice = homeInfo.notice.notices,
                kokkos = homeInfo.kokkoks.kokkoksNews,
                slider = homeInfo.banner.banners
            )
        }
    }


}