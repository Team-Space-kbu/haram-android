package com.space.core_ui.binding.adapter.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.util.NonParamsItemHandler
import com.space.core_ui.databinding.ItemButtonNonParamsBinding


class ButtonAdapter(
    private val text: String,
    private val itemHandler: NonParamsItemHandler
) : RecyclerView.Adapter<ButtonViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ButtonViewHolder {
        return ButtonViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) =
        holder.itemBind(text, itemHandler)

    override fun getItemCount(): Int = 1
}

class ButtonViewHolder(
    private val binding: ItemButtonNonParamsBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): ButtonViewHolder {
            val binding =
                ItemButtonNonParamsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ButtonViewHolder(binding)
        }
    }

    fun itemBind(
        text: String,
        itemHandler: NonParamsItemHandler
    ) {
        binding.setVariable(BR.buttonTitle, text)
        binding.setVariable(BR.buttonNonHandler, itemHandler)
        binding.executePendingBindings()
    }
}