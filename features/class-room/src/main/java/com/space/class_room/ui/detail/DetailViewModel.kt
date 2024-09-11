package com.space.class_room.ui.detail

import androidx.lifecycle.viewModelScope
import com.space.core_ui.base.BaseViewModel
import com.space.domain.course.ClassRoomDetailUseCase
import com.space.shared.UiStatus
import com.space.shared.UiStatusType
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val classRoomDetailUseCase: ClassRoomDetailUseCase
) : BaseViewModel<List<String>>() {

    fun getDetail(string: String){
        viewModelScope.launch {
            val result = async { classRoomDetailUseCase(string) }.await()
            result.mapCatching(
                onSuccess = {
                    _view.value = UiStatus(UiStatusType.SUCCESS, it)
                },
                onError = ::setIntranetData
            )
        }
    }
}