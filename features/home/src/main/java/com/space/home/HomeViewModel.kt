package com.space.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.shared.data.home.Home
import com.space.domain.usecase.home.HomeUseCase
import com.space.domain.usecase.home.ShortcutUseCase
import com.space.navigator.view.NavigatorBible
import com.space.navigator.view.NavigatorBook
import com.space.navigator.view.NavigatorChapel
import com.space.navigator.view.NavigatorMileage
import com.space.navigator.view.NavigatorPartners
import com.space.navigator.view.NavigatorRothem
import com.space.navigator.view.NavigatorTimetable
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
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

    @Inject
    lateinit var navigatorRothem: NavigatorRothem

    @Inject
    lateinit var navigatorTimetable: NavigatorTimetable

    private val _homeInfo = MutableLiveData<Home>()
    val homeInfo: LiveData<Home> = _homeInfo


    init {
        viewModelScope.launch {
            val info = async { homeUseCase() }.await()
            info.mapCatching(
                onSuccess = { home ->
                    _homeInfo.value = Home(
                        notice = home.notice.notices.ifEmpty { emptyList() },
                        kokkos = home.kokkoks.kokkoksNews.ifEmpty { emptyList() },
                        shortcut = emptyList(),
                        slider = home.banner.banners.ifEmpty { emptyList() }
                    )
                },
                onError = { error ->
                    Timber.i(error.message)
                }
            )
        }
    }


}