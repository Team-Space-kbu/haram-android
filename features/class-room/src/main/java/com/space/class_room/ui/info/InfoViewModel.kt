package com.space.class_room.ui.info

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.course.ClassRoomInfoUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.course.Course
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val classRoomInfoUseCase: ClassRoomInfoUseCase
) : BaseViewModel<List<Course>>() {
    val day = arrayOf("월", "화", "수", "목", "금")
    val colorList = listOf(
        "#83a3e4", "#e28b7b", "#9b87db",
        "#8bc88e", "#f0af72", "#90cfc1",
        "#F2D96D", "#D397ED", "#A7CA70",
        "#FFC8A2", "#FFC5BF", "#8FCACA",
        "#CCE2CB", "#B6CFB6", "#97C1A9",
        "#FF968A", "#CBAACB", "#F3B0C3"
    )

    val scheduleColor = HashMap<String, String>()

    fun setInit(string: String){
        viewModelScope.launch {
            val time = async { classRoomInfoUseCase(string) }.await()
            time.mapCatching(
                onSuccess = { timetables ->
                    _view.value = UiStatus(UiStatusType.SUCCESS, timetables)
                },
                onError = ::setIntranetData
            )
        }
    }
}