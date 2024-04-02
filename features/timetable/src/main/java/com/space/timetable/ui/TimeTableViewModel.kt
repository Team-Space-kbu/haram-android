package com.space.timetable.ui

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.timetable.TimetableUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.timetable.Timetable
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeTableViewModel @Inject constructor(
    private val timetableUseCase: TimetableUseCase
) : BaseViewModel<List<Timetable>>() {

    val day = arrayOf("월", "화", "수", "목", "금")
    val colorList = listOf(
        "#83a3e4", "#e28b7b", "#9b87db",
        "#8bc88e", "#f0af72", "#90cfc1",
        "#F2D96D", "#D397ED", "#A7CA70",
        "#F29F05", "#03318C", "#F2C5BB",
        "#D9736A", "#9ABF4B", "#A64F03",
        "#0455BF", "#024059", "#F27649"
    )
    val scheduleColor = HashMap<String, String>()

    init {
        viewModelScope.launch {
            val time = async { timetableUseCase() }.await()
            time.mapCatching(
                onSuccess = { timetables ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, timetables)
                },
                onError = ::setIntranetData
            )
        }
    }
}