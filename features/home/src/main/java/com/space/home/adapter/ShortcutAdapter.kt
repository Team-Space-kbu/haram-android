package com.space.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.space.home.R
import com.space.home.databinding.ItemShortcutTextBinding

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
        fun clickShortcut()
    }
}

internal class ShortcutViewHolder(
    view: View,
    private val itemHandler: ShortcutAdapter.ItemHandler
) : RecyclerView.ViewHolder(view) {
//
//    private val binding: ItemShortcutTextBinding
//
//    init {
//        binding = DataBindingUtil.bind(view)!!
//    }

    companion object {
        fun newInstance(
            parent: ViewGroup,
            itemHandler: ShortcutAdapter.ItemHandler
        ): ShortcutViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_info_shortcut, parent, false)
            return ShortcutViewHolder(view, itemHandler)
        }
    }

    fun itemBind() {
        itemView.findViewById<TextView>(R.id.book).setOnClickListener {
            itemHandler.clickShortcut()
        }
    }
}