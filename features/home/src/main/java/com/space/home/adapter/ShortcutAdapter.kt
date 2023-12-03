package com.space.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.space.book.databinding.ItemBookInfoBinding
import com.space.home.R
import com.space.home.databinding.ItemInfoShortcutBinding
import com.space.home.databinding.ItemShortcutTextBinding
import com.space.home.util.ViewType

internal class ShortcutAdapter(
    private val itemHandler: ItemHandler
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShortcutViewHolder.newInstance(parent, itemHandler)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ShortcutViewHolder).itemBind()
    }

    override fun getItemCount(): Int = 1

    interface ItemHandler {
        fun clickShortcut(viewType: ViewType)
    }
}

internal class ShortcutViewHolder(
    private val binding: ItemInfoShortcutBinding,
    private val itemHandler: ShortcutAdapter.ItemHandler
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(
            parent: ViewGroup,
            itemHandler: ShortcutAdapter.ItemHandler
        ): ShortcutViewHolder {
            val binding = ItemInfoShortcutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ShortcutViewHolder(binding, itemHandler)
        }
    }

    fun itemBind() {
        binding.book.setOnClickListener {
            itemHandler.clickShortcut(viewType = ViewType.BOOK_HOME)
        }
    }
}