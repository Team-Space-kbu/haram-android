package com.space.mileage.ui.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.mileage.BR
import com.space.mileage.databinding.ItemMileageDetailBinding
import com.space.shared.data.mileage.MileageDetail
import java.text.DecimalFormat

internal class MileageItemAdapter(
    private val item: List<MileageDetail>
) : RecyclerView.Adapter<MileageItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MileageItemViewHolder =
        MileageItemViewHolder.newInstance(parent)

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: MileageItemViewHolder, position: Int) =
        holder.itemBind(item[position])


}

internal class MileageItemViewHolder(
    private val binding: ItemMileageDetailBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): MileageItemViewHolder {
            val binding =
                ItemMileageDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MileageItemViewHolder(binding)
        }
    }

    fun itemBind(mileageDetail: MileageDetail) {
        binding.setVariable(BR.mileage, mileageDetail)
    }
}

