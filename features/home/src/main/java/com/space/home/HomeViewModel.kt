package com.space.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.chapel.ChapelUseCase
import com.space.shared.data.home.Home
import com.space.domain.home.HomeUseCase
import com.space.domain.home.ShortcutUseCase
import com.space.navigator.view.NavigatorBible
import com.space.navigator.view.NavigatorBook
import com.space.navigator.view.NavigatorChapel
import com.space.navigator.view.NavigatorMileage
import com.space.navigator.view.NavigatorNotice
import com.space.navigator.view.NavigatorNoticeSpace
import com.space.navigator.view.NavigatorPartners
import com.space.navigator.view.NavigatorRothem
import com.space.navigator.view.NavigatorTimetable
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.chapel.ChapelInfo
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val chapelUseCase: ChapelUseCase,
    private val shortUseCase: ShortcutUseCase,
) : BaseViewModel<Home>() {

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

    @Inject
    lateinit var navigatorNoticeSpace: NavigatorNoticeSpace

    private val _chapel = MutableLiveData<ChapelInfo>()
    val chapel: LiveData<ChapelInfo> = _chapel

    init {
        viewModelScope.launch {
            val info = async { homeUseCase() }.await()
            info.mapCatching(
                onSuccess = { home ->
                    _view.value = UiStatus(
                        UiStatusType.SUCCESS,
                        Home(
                            notice = home.notice.notices.ifEmpty { emptyList() },
                            kokkos = home.kokkoks.kokkoksNews.ifEmpty { emptyList() },
                            shortcut = emptyList(),
                            slider = home.banner.banners.ifEmpty { emptyList() }
                        )
                    )
                },
                onError = ::setIntranetData
            )
            if (chapelTime()) {
                val chapel = async { chapelUseCase() }.await()
                chapel.mapCatching(
                    onSuccess = {
                        _chapel.value = it.chapelInfo
                    }, onError = {
                        Timber.i(it.message)
                    }
                )
            }
        }

    }

    fun chapelTime(): Boolean =
        LocalTime.now() in LocalTime.of(10, 0)..LocalTime.of(13, 0)


}