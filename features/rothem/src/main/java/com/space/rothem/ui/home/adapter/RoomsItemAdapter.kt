package com.space.rothem.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.ParamsItemHandler
import com.space.rothem.BR
import com.space.rothem.databinding.ItemRothemRoomRightBinding
import com.space.shared.data.rothem.Room

internal class RoomsItemAdapter(
    private val sliders: List<Room>,
    private val itemHandler: ParamsItemHandler<Room>
) : RecyclerView.Adapter<ItemRoomsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRoomsViewHolder =
        ItemRoomsViewHolder.newInstance(parent)

    override fun getItemCount() = sliders.size

    override fun onBindViewHolder(holder: ItemRoomsViewHolder, position: Int) =
        holder.bindItem(sliders[position], itemHandler)

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
