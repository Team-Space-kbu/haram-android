package com.space.timetable.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.domain.usecase.timetable.TimetableUseCase
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
) : ViewModel() {

    private val _timetable: MutableLiveData<List<Timetable>> = MutableLiveData<List<Timetable>>()
    val timetable: LiveData<List<Timetable>> = _timetable

    val day = arrayOf("월", "화", "수", "목", "금")

    init {
        viewModelScope.launch {
            val time = async { timetableUseCase() }.await()
            time.mapCatching(
                onSuccess = { timetables ->
                    _timetable.value = timetables
                },
                onError = { error ->
                    Timber.d(error.message)
                }
            )
        }
    }
}