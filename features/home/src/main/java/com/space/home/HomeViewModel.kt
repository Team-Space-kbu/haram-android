package com.space.home

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.shared.model.home.HomeModel
import com.space.domain.home.HomeUseCase
import com.space.navigator.view.NavigatorBible
import com.space.navigator.view.NavigatorBook
import com.space.navigator.view.NavigatorChapel
import com.space.navigator.view.NavigatorClassRoom
import com.space.navigator.view.NavigatorCourse
import com.space.navigator.view.NavigatorMileage
import com.space.navigator.view.NavigatorNotice
import com.space.navigator.view.NavigatorNoticeSpace
import com.space.navigator.view.NavigatorPartners
import com.space.navigator.view.NavigatorPdf
import com.space.navigator.view.NavigatorRothem
import com.space.navigator.view.NavigatorTimetable
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : BaseViewModel<HomeModel>() {

    @Inject
    lateinit var navigatorBook: NavigatorBook

    @Inject
    lateinit var navigatorMileage: NavigatorMileage

    @Inject
    lateinit var navigatorChapel: NavigatorChapel

    @Inject
    lateinit var navigatorPartners: NavigatorPartners

    @Inject
    lateinit var navigatorRothem: NavigatorRothem

    @Inject
    lateinit var navigatorTimetable: NavigatorTimetable

    @Inject
    lateinit var navigatorNotice: NavigatorNotice

    @Inject
    lateinit var navigatorNoticeSpace: NavigatorNoticeSpace

    @Inject
    lateinit var navigatorClassRoom: NavigatorClassRoom

    @Inject
    lateinit var navigatorCourse: NavigatorCourse

    @Inject
    lateinit var navigatorPdf: NavigatorPdf

    init {
        viewModelScope.launch {
            val info = async { homeUseCase() }.await()
            info.mapCatching(
                onSuccess = { home ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, home)
                },
                onError = ::setIntranetData
            )
        }

    }
}