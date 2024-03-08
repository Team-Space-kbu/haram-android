package com.space.rothem.ui.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.core_ui.R
import com.space.domain.usecase.rothem.RothemReservationsDetail
import com.space.shared.data.rothem.ReservationCalendar
import com.space.shared.data.rothem.RoomReservation
import com.space.shared.mapCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val rothemReservationsDetail: RothemReservationsDetail
) : ViewModel() {

    private val _rothem: MutableLiveData<RoomReservation> = MutableLiveData<RoomReservation>()
    val rothem: LiveData<RoomReservation> = _rothem


    val selectCalender = MutableLiveData<ReservationCalendar>()


    fun getReservationInfo(seq: String) {
        viewModelScope.launch {
            val result = async { rothemReservationsDetail(seq) }.await()
            result.mapCatching(
                onSuccess = { reservation ->
                    _rothem.value = reservation
                    val current =
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd"))
                    reservation.calendarResponses.forEach {
                        if (current.toInt().toString() == it.date) {
                            selectCalender.value = it
                        }
                    }
                },
                onError = { throwable ->
                    Timber.d(throwable.message)
                }
            )
        }
    }

}