package com.space.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.ParamsItemHandler
import com.space.home.BR
import com.space.home.databinding.ItemInfoShortcutBinding
import com.space.home.util.ViewType
import com.space.navigator.UiNavigator

internal class ShortcutAdapter(
    private val itemHandler: ParamsItemHandler<UiNavigator>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShortcutViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ShortcutViewHolder).itemBind(itemHandler)
    }

    override fun getItemCount(): Int = 1

}

internal class ShortcutViewHolder(
    private val binding: ItemInfoShortcutBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup
        ): ShortcutViewHolder {
            val binding = ItemInfoShortcutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ShortcutViewHolder(binding)
        }
    }

    fun itemBind(itemHandler: ParamsItemHandler<UiNavigator>) {
        binding.setVariable(BR.handlerShortcut, itemHandler)
    }
}