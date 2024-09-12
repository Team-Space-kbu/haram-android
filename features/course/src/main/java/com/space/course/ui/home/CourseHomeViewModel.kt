package com.space.course.ui.home

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.course.CourseUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.course.CourseHome
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseHomeViewModel @Inject constructor(
    private val courseUseCase: CourseUseCase
) : BaseViewModel<List<CourseHome>>() {

    init {
        viewModelScope.launch {
            val result = async { courseUseCase() }.await()
            result.mapCatching(
                onSuccess = {
                    _view.value = UiStatus(UiStatusType.SUCCESS, it)
                },
                onError = ::setIntranetData
            )
        }
    }
}