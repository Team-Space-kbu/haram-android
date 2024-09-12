package com.space.course.ui.course

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.course.CourseDetailsUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.data.course.Course
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseDetailViewModel @Inject constructor(
    private val courseDetailsUseCase: CourseDetailsUseCase
) : BaseViewModel<List<Course>>() {

    fun init(string: String) {
        viewModelScope.launch {
            val result = async { courseDetailsUseCase(string) }.await()
            result.mapCatching(
                onSuccess = {
                    _view.value = UiStatus(UiStatusType.SUCCESS, it)
                },
                onError = ::setIntranetData
            )
        }
    }
}