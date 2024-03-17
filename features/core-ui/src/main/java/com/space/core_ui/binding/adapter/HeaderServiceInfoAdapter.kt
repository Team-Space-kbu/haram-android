package com.space.core_ui.binding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.databinding.ItemServiceInfoBinding

class HeaderServiceInfoAdapter(
    private val title: String,
    private val text: String
) : RecyclerView.Adapter<HeaderServiceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderServiceViewHolder {
        return HeaderServiceViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: HeaderServiceViewHolder, position: Int) =
        holder.itemBind(title, text)

    override fun getItemCount(): Int = 1

}

class HeaderServiceViewHolder(
    private val binding: ItemServiceInfoBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): HeaderServiceViewHolder {
            val binding =
                ItemServiceInfoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return HeaderServiceViewHolder(binding)
        }
    }

    fun itemBind(
        title: String,
        text: String
    ) {
        binding.setVariable(BR.titleInfo, title)
        binding.setVariable(BR.textInfo, text)
    }
}