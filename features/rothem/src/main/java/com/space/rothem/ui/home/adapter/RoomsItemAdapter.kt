package com.space.rothem.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.ParamsItemHandler
import com.space.core_ui.view.holder.ItemEmptyViewHolder
import com.space.rothem.BR
import com.space.rothem.databinding.ItemRothemRoomRightBinding
import com.space.shared.data.rothem.Room

internal class RoomsItemAdapter(
    private val rooms: List<Room>,
    private val itemHandler: ParamsItemHandler<Room>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (rooms.isEmpty())
            ItemEmptyViewHolder.newInstance(parent)
        else
            ItemRoomsViewHolder.newInstance(parent)

    override fun getItemCount() = if (rooms.isEmpty()) 1 else rooms.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemRoomsViewHolder -> holder.bindItem(rooms[position], itemHandler)
            is ItemEmptyViewHolder -> holder.bindItem("예약가능한 방이 존재하지 않습니다.")
        }
    }

}

internal class ItemRoomsViewHolder(
    private val binding: ItemRothemRoomRightBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup
        ): ItemRoomsViewHolder {
            val binding = ItemRothemRoomRightBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ItemRoomsViewHolder(binding)
        }
    }

    fun bindItem(room: Room, itemHandler: ParamsItemHandler<Room>) {
        binding.linear.setPadding(50, 0, 50, 0)
        binding.setVariable(BR.room, room)
        binding.setVariable(BR.handler, itemHandler)
    }

}
