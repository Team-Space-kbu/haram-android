package com.space.timetable.ui

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseIntranetViewModel
import com.space.domain.usecase.timetable.TimetableUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.timetable.Timetable
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TimeTableViewModel @Inject constructor(
    private val timetableUseCase: TimetableUseCase
) : BaseIntranetViewModel<List<Timetable>>() {

    val day = arrayOf("월", "화", "수", "목", "금")
    val colorList = listOf(
        "#83a3e4", "#e28b7b", "#9b87db",
        "#8bc88e", "#f0af72", "#90cfc1",
        "#F2D96D", "#D397ED", "#A7CA70")
    val scheduleColor = HashMap<String, String>()


    init {
        viewModelScope.launch {
            val time = async { timetableUseCase() }.await()
            time.mapCatching(
                onSuccess = { timetables ->
                    _liveData.value = UiStatus(UiStatusType.SUCCESS, timetables)
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                    setIntranetData(throwable)
                }
            )
        }
    }
}