package com.space.rothem.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.ParamsItemHandler
import com.space.rothem.BR
import com.space.rothem.databinding.ItemRothemInfoBinding
import com.space.shared.data.rothem.ReservationCalendar

internal class SelectTimeAdapter(
    private val calendars: List<ReservationCalendar>,
    private val selectCalender : MutableLiveData<ReservationCalendar>
) : RecyclerView.Adapter<SelectTimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTimeViewHolder =
        SelectTimeViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: SelectTimeViewHolder, position: Int) =
        holder.itemBind(calendars, selectCalender)

}

internal class SelectTimeViewHolder(
    private val binding: ItemRothemInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): SelectTimeViewHolder {
            val binding =
                ItemRothemInfoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return SelectTimeViewHolder(binding)
        }
    }

    fun itemBind(
        calendars: List<ReservationCalendar>,
        selectCalender : MutableLiveData<ReservationCalendar>
    ) {
        binding.setVariable(BR.imgTitle, "시간선택")
        binding.recyclerView.layoutManager = LinearLayoutManager(
            itemView.context,
            RecyclerView.HORIZONTAL,
            false
        )
        binding.recyclerView.adapter = CalendarItemAdapter(calendars, selectCalender)
    }
}

