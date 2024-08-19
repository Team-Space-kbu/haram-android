package com.space.core_ui.binding.adapter.func

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.core_ui.BR
import com.space.core_ui.util.NonParamsItemHandler
import com.space.core_ui.databinding.ItemFuncBinding
import com.space.shared.data.core_ui.Func

class FuncAdapter<T>(
    private val info: Func<T>,
    private val nonParamsItemHandler: NonParamsItemHandler
) : RecyclerView.Adapter<FuncViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuncViewHolder {
        return FuncViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: FuncViewHolder, position: Int) =
        holder.itemBind(info, nonParamsItemHandler)

    override fun getItemCount(): Int = 1

}

class FuncViewHolder(
    private val binding: ItemFuncBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): FuncViewHolder {
            val binding =
                ItemFuncBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return FuncViewHolder(binding)
        }
    }

    fun <T> itemBind(
        info: Func<T>,
        nonParamsItemHandler: NonParamsItemHandler
    ) {
        binding.setVariable(BR.title, info.title)
        binding.setVariable(BR.handler, nonParamsItemHandler)
        Glide.with(itemView.context)
            .load(info.image)
            .centerCrop()
            .into(binding.imageView)
    }
}