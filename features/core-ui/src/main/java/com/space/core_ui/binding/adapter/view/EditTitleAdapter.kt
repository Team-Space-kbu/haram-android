package com.space.core_ui.binding.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.databinding.ItemEditTitleBinding

class EditTitleAdapter(
    private val title: String
) : RecyclerView.Adapter<EditTitleHeaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditTitleHeaderViewHolder {
        return EditTitleHeaderViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: EditTitleHeaderViewHolder, position: Int) =
        holder.itemBind(title)

    override fun getItemCount(): Int = 1

}

class EditTitleHeaderViewHolder(
    private val binding: ItemEditTitleBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): EditTitleHeaderViewHolder {
            val binding = ItemEditTitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return EditTitleHeaderViewHolder(binding)
        }
    }

    fun itemBind(
        title: String
    ) {
        binding.setVariable(BR.titleEdit, title)
    }
}