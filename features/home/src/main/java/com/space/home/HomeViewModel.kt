package com.space.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.shared.data.home.Home
import com.space.shared.data.home.Shortcut
import com.space.shared.result.succeeded
import com.space.shared.result.successOr
import com.space.domain.usecase.home.HomeUseCase
import com.space.domain.usecase.home.ShortcutUseCase
import com.space.navigator.NavigatorBible
import com.space.navigator.NavigatorBook
import com.space.navigator.NavigatorChapel
import com.space.navigator.NavigatorMileage
import com.space.navigator.NavigatorPartners
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val shortUseCase: ShortcutUseCase,
) : ViewModel() {

    @Inject
    lateinit var navigatorBook: NavigatorBook

    @Inject
    lateinit var navigatorMileage: NavigatorMileage

    @Inject
    lateinit var navigatorChapel: NavigatorChapel

    @Inject
    lateinit var navigatorPartners: NavigatorPartners

    @Inject
    lateinit var navigatorBible: NavigatorBible


    private val _homeInfo = MutableLiveData<Home>()
    val homeInfo: LiveData<Home> = _homeInfo

    private val _shortcut = MutableLiveData<List<Shortcut>>()
    val shortcut: LiveData<List<Shortcut>> = _shortcut


    init {
        viewModelScope.launch {
            val homeInfo = async { homeUseCase() }.await().succeeded()
            val shortcut = async { shortUseCase() }.await()
            _homeInfo.value = Home(
                notice = homeInfo?.notice!!.notices.ifEmpty { emptyList() },
                kokkos = homeInfo.kokkoks.kokkoksNews.ifEmpty { emptyList() },
                shortcut = shortcut.successOr(emptyList()),
                slider = homeInfo.banner.banners.ifEmpty { emptyList() }
            )
        }
    }


}