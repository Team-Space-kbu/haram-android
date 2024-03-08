package com.space.rothem.ui.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.space.shared.data.rothem.Amenity
import com.space.rothem.BR
import com.space.rothem.databinding.ItemRothemInfoBinding

internal class RoomAmenitiesAdapter(
    private val amenity: List<Amenity>
) : RecyclerView.Adapter<RoomAmenitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomAmenitiesViewHolder =
        RoomAmenitiesViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: RoomAmenitiesViewHolder, position: Int) =
        holder.itemBind(amenity)

}

internal class RoomAmenitiesViewHolder(
    private val binding: ItemRothemInfoBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): RoomAmenitiesViewHolder {
            val binding =
                ItemRothemInfoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return RoomAmenitiesViewHolder(binding)
        }
    }

    fun itemBind(amenity: List<Amenity>) {
        binding.setVariable(BR.imgTitle, "Popular amenities")
        binding.recyclerView.adapter = AmenitiesItemAdapter(amenity)
        binding.recyclerView.layoutManager = FlexboxLayoutManager(itemView.context).apply {
            justifyContent = JustifyContent.FLEX_START
        }
    }
}

