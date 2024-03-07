package com.space.core_ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.DividerItemDecoration
import com.space.core_ui.ParamsItemHandler
import com.space.core_ui.R
import com.space.core_ui.databinding.ItemBottomParamsbuttonBinding


class BottomParamsButtonAdapter(
    private val text: String,
    private val params: String,
    private val adapter: ConcatAdapter,
    private val itemHandler: ParamsItemHandler<String>
) : RecyclerView.Adapter<BottomParamsButtonViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomParamsButtonViewHolder {
        return BottomParamsButtonViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: BottomParamsButtonViewHolder, position: Int) =
        holder.itemBind(text, params, adapter, itemHandler)

    override fun getItemCount(): Int = 1
}

class BottomParamsButtonViewHolder(
    private val binding: ItemBottomParamsbuttonBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): BottomParamsButtonViewHolder {
            val binding =
                ItemBottomParamsbuttonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return BottomParamsButtonViewHolder(binding)
        }
    }

    fun itemBind(
        text: String,
        params: String,
        adapter: ConcatAdapter,
        itemHandler: ParamsItemHandler<String>
    ) {
        binding.setVariable(BR.buttonTitle, text)
        binding.setVariable(BR.buttonParams, params)
        binding.setVariable(BR.buttonHandler, itemHandler)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.isNestedScrollingEnabled = false
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                itemView.context,
                R.drawable.line_divider,
                5,
                5,
                2
            )
        )
    }
}