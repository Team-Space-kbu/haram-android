package com.space.rothem.ui.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.rothem.BR
import com.space.shared.data.rothem.Amenity
import com.space.rothem.databinding.ItemAmenitiesBinding

internal class AmenitiesItemAdapter(
    private val amenity: List<Amenity>
) : RecyclerView.Adapter<AmenitiesItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmenitiesItemViewHolder =
        AmenitiesItemViewHolder.newInstance(parent)

    override fun getItemCount() = amenity.size

    override fun onBindViewHolder(holder: AmenitiesItemViewHolder, position: Int) =
        holder.itemBind(amenity[position])

}

internal class AmenitiesItemViewHolder(
    private val binding: ItemAmenitiesBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): AmenitiesItemViewHolder {
            val binding =
                ItemAmenitiesBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return AmenitiesItemViewHolder(binding)
        }
    }

    fun itemBind(amenity: Amenity) {
        binding.setVariable(BR.amenity, amenity)
    }
}

