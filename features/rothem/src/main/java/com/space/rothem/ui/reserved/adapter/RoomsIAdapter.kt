package com.space.rothem.ui.reserved.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.rothem.BR
import com.space.rothem.databinding.ItemRothemRoomLeftBinding
import com.space.shared.data.rothem.Room

internal class RoomsAdapter(
    private val room: Room,
) : RecyclerView.Adapter<ItemRoomsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRoomsViewHolder =
        ItemRoomsViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ItemRoomsViewHolder, position: Int) =
        holder.bindItem(room)

}

internal class ItemRoomsViewHolder(
    private val binding: ItemRothemRoomLeftBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(
            parent: ViewGroup
        ): ItemRoomsViewHolder {
            val binding = ItemRothemRoomLeftBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ItemRoomsViewHolder(binding)
        }
    }

    fun bindItem(room: Room) {
        binding.setVariable(BR.room, room)
    }

}
