package com.space.core_ui.binding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.NonParamsItemHandler
import com.space.core_ui.databinding.Item2wayButtonBinding

import com.space.core_ui.databinding.ItemBottomButtonBinding


class Fill2wayButtonAdapter(
    private val adapter: ConcatAdapter,
    private val cancelHandler: NonParamsItemHandler,
    private val nextHandler: NonParamsItemHandler
) : RecyclerView.Adapter<Fill2wayButtonViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Fill2wayButtonViewHolder {
        return Fill2wayButtonViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: Fill2wayButtonViewHolder, position: Int) =
        holder.itemBind(adapter, cancelHandler, nextHandler)

    override fun getItemCount(): Int = 1
}

class Fill2wayButtonViewHolder(
    private val binding: Item2wayButtonBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): Fill2wayButtonViewHolder {
            val binding =
                Item2wayButtonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return Fill2wayButtonViewHolder(binding)
        }
    }

    fun itemBind(
        adapter: ConcatAdapter,
        cancelHandler: NonParamsItemHandler,
        nextHandler: NonParamsItemHandler
    ) {
        binding.setVariable(BR.cancelButtonHandler, cancelHandler)
        binding.setVariable(BR.nextButtonHandler, nextHandler)
        binding.recyclerView.adapter = adapter
    }
}