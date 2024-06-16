package com.space.shared.model

import androidx.lifecycle.MutableLiveData
import com.space.shared.data.rothem.ReservationCalendar

data class RothemReservedModel(
    val selectCalender: MutableLiveData<ReservationCalendar> = MutableLiveData<ReservationCalendar>(),
    val nameLive: MutableLiveData<String> = MutableLiveData<String>(),
    val cellPhone: MutableLiveData<String> = MutableLiveData<String>()
)
