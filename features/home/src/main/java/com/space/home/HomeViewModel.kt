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
import com.space.navigator.view.NavigatorNotice
import com.space.navigator.view.NavigatorPartners
import com.space.navigator.view.NavigatorRothem
import com.space.navigator.view.NavigatorTimetable
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException
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

    @Inject
    lateinit var navigatorNotice: NavigatorNotice

    private val _homeInfo = MutableLiveData<UiStatus<Home>>()
    val homeInfo: LiveData<UiStatus<Home>> = _homeInfo


    init {
        viewModelScope.launch {
            val info = async { homeUseCase() }.await()
            info.mapCatching(
                onSuccess = { home ->
                    _homeInfo.value = UiStatus(
                        UiStatusType.SUCCESS,
                        Home(
                            notice = home.notice.notices.ifEmpty { emptyList() },
                            kokkos = home.kokkoks.kokkoksNews.ifEmpty { emptyList() },
                            shortcut = emptyList(),
                            slider = home.banner.banners.ifEmpty { emptyList() }
                        )
                    )
                },
                onError = { throwable ->
                    Timber.i(throwable.message)
                    when (throwable) {
                        is UnknownHostException -> {
                            _homeInfo.value = UiStatus(UiStatusType.NO_CONNECTION)
                        }
                        is SocketTimeoutException->{
                            _homeInfo.value = UiStatus(UiStatusType.NO_CONNECTION)
                        }
                        is Exception -> {
                            _homeInfo.value = UiStatus(UiStatusType.ERROR)
                        }
                    }
                }
            )
        }
    }


}