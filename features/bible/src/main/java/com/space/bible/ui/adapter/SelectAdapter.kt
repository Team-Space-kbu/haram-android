package com.space.bible.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.databinding.ItemHeaderBinding


internal class SelectAdapter(
    private val item: List<String>,
    private val itemHandler: ItemHandler
) : RecyclerView.Adapter<SelectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectViewHolder =
        SelectViewHolder.newInstance(parent)

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: SelectViewHolder, position: Int) {
        holder.itemBind(itemHandler, item[position])
    }

    interface ItemHandler {
        fun clickSelect(string: String)
    }
}

internal class SelectViewHolder(
    private val binding: ItemHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
        ): SelectViewHolder {
            val binding = ItemHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return SelectViewHolder(binding)
        }
    }

    fun itemBind(itemHandler: SelectAdapter.ItemHandler, string: String) {
        binding.headerTitle.text = string
        binding.headerTitle.setPadding(10)
        binding.headerTitle.setOnClickListener {
            itemHandler.clickSelect(string)
        }
    }

}
