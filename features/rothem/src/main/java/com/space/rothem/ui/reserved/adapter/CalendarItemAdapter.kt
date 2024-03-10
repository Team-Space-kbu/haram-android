package com.space.rothem.ui.reserved.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.space.rothem.BR
import com.space.rothem.databinding.ItemRothemCalenderBinding
import com.space.shared.data.rothem.ReservationCalendar

internal class CalendarItemAdapter(
    private val calendars: List<ReservationCalendar>,
    private val selectCalender: MutableLiveData<ReservationCalendar>
) : RecyclerView.Adapter<CalendarItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemViewHolder =
        CalendarItemViewHolder.newInstance(parent)

    override fun getItemCount() = calendars.size

    override fun onBindViewHolder(holder: CalendarItemViewHolder, position: Int) =
        holder.itemBind(calendars[position], selectCalender)

}

internal class CalendarItemViewHolder(
    private val binding: ItemRothemCalenderBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): CalendarItemViewHolder {
            val binding =
                ItemRothemCalenderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return CalendarItemViewHolder(binding)
        }
    }

    init {
        itemView.doOnAttach {
            binding.lifecycleOwner = itemView.findViewTreeLifecycleOwner()
        }
        itemView.doOnDetach {
            binding.lifecycleOwner = null
        }
    }


    fun itemBind(
        calendars: ReservationCalendar,
        selectCalender: MutableLiveData<ReservationCalendar>
    ) {
        binding.setVariable(BR.calender, calendars)
        binding.setVariable(BR.live, selectCalender)
    }

}

