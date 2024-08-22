package com.space.mileage.ui.databinding.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.view.holder.ItemEmptyViewHolder
import com.space.mileage.BR
import com.space.mileage.databinding.ItemMileageDetailBinding
import com.space.shared.data.mileage.MileageDetail

internal class MileageItemAdapter(
    private val item: MutableList<MileageDetail>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(
        details: List<MileageDetail>,
    ) {
        item.addAll(details)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (item.isEmpty())
            ItemEmptyViewHolder.newInstance(parent)
        else
            MileageItemViewHolder.newInstance(parent)

    override fun getItemCount() = if (item.isEmpty()) 1 else item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MileageItemViewHolder -> holder.itemBind(item[position])
            is ItemEmptyViewHolder -> holder.bindItem("마일리지 정보가 없습니다.")
        }
    }


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

