package com.space.mileage.ui.databinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.mileage.BR
import com.space.mileage.databinding.ItemMileageBalanceBinding
import com.space.shared.data.mileage.Mileage

internal class MileageBalanceAdapter(
    private val mileage: Mileage
) : RecyclerView.Adapter<MileageBalanceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MileageBalanceViewHolder =
        MileageBalanceViewHolder.newInstance(parent)

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: MileageBalanceViewHolder, position: Int) =
        holder.bindItem(mileage)


}

internal class MileageBalanceViewHolder(
    private val binding: ItemMileageBalanceBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): MileageBalanceViewHolder {
            val binding =
                ItemMileageBalanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MileageBalanceViewHolder(binding)
        }
    }

    fun bindItem(mileage: Mileage) {
       binding.setVariable(BR.mileage, mileage)
    }
}

