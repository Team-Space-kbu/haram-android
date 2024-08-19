package com.space.other.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.util.NonParamsItemHandler
import com.space.other.databinding.ItemCatalogBinding

class ItemCatalogAdapter(
    private val title: String,
    @ColorInt private val color: Int? = Color.parseColor("#1A1E27"),
    private val nonParamsItemHandler: NonParamsItemHandler
) : RecyclerView.Adapter<CatalogViewHolder>() {

    fun setColor() {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        return CatalogViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) =
        holder.itemBind(title, color!!, nonParamsItemHandler)

    override fun getItemCount(): Int = 1

}

class CatalogViewHolder(
    private val binding: ItemCatalogBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): CatalogViewHolder {
            val binding =
                ItemCatalogBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return CatalogViewHolder(binding)
        }
    }

    fun itemBind(
        title: String,
        @ColorInt color: Int,
        nonParamsItemHandler: NonParamsItemHandler
    ) {
        binding.setVariable(BR.title, title)
        binding.setVariable(BR.handler, nonParamsItemHandler)
        binding.title.setTextColor(color)
    }
}