package com.space.rothem.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.google.android.material.card.MaterialCardView
import com.space.core_ui.R
import com.space.shared.data.rothem.ReservationCalendar


@BindingAdapter("toKorean")
fun toKoreaVersion(
    textView: TextView,
    string: String
) {
    textView.text =
        when (string) {
            "MONDAY" -> {
                "월요일"
            }

            "TUESDAY" -> {
                "화요일"
            }

            "WEDNESDAY" -> {
                "수요일"
            }

            "THURSDAY" -> {
                "목요일"
            }

            "FRIDAY" -> {
                "금요일"
            }

            else -> {
                "알수없음"
            }
        }
}

@BindingAdapter("setCalendar", "presentCalendar")
fun setTextColor(
    textView: TextView,
    calendars: ReservationCalendar,
    selectCalender: MutableLiveData<ReservationCalendar>
) {
    if (calendars.isAvailable) {
        val date = selectCalender.value?.calendarSeq
        if (date == calendars.calendarSeq) {
            textView.setTextColor(textView.context.getColor(R.color.white))
        } else {
            textView.setTextColor(textView.context.getColor(R.color.black))
        }
    } else {
        textView.setTextColor(textView.context.getColor(R.color.white))
    }
}

@BindingAdapter("setCalendar", "presentCalendar")
fun setTextColor(
    materialCardView: MaterialCardView,
    calendars: ReservationCalendar,
    selectCalender: MutableLiveData<ReservationCalendar>
) {
    if (calendars.isAvailable) {
        val date = selectCalender.value?.calendarSeq
        if (date == calendars.calendarSeq) {
            materialCardView.setCardBackgroundColor(materialCardView.context.getColor(R.color.greenBackground))
        } else {
            materialCardView.setCardBackgroundColor(materialCardView.context.getColor(R.color.white))
        }
        materialCardView.setOnClickListener {
            selectCalender.value = calendars
        }
    } else {
        materialCardView.setCardBackgroundColor(materialCardView.context.getColor(R.color.deepDarkBlue))
    }
}
