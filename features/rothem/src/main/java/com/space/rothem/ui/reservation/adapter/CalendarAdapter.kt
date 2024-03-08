package com.space.rothem.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.rothem.BR
import com.space.rothem.databinding.ItemRothemInfoBinding
import com.space.shared.data.rothem.ReservationCalendar

internal class CalendarAdapter(
    private val calendars: List<ReservationCalendar>,
    private val selectCalender: MutableLiveData<ReservationCalendar>
) : RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder =
        CalendarViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) =
        holder.itemBind(calendars, selectCalender)

}

internal class CalendarViewHolder(
    private val binding: ItemRothemInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): CalendarViewHolder {
            val binding =
                ItemRothemInfoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return CalendarViewHolder(binding)
        }
    }

    fun itemBind(
        calendars: List<ReservationCalendar>,
        selectCalender: MutableLiveData<ReservationCalendar>
    ) {
        binding.setVariable(BR.imgTitle, "날짜 선택")
        binding.recyclerView.layoutManager =
            GridLayoutManager(
                itemView.context,
                calendars.size,
                RecyclerView.VERTICAL,
                false
            )
        binding.recyclerView.adapter = CalendarItemAdapter(calendars, selectCalender)
    }
}

