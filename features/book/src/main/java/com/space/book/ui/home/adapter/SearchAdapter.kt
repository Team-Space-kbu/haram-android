package com.space.book.ui.home.adapter

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.RecyclerView
import com.space.book.databinding.ItemBookSearchBinding
import com.space.core_ui.util.ParamsItemHandler
import com.space.core_ui.extension.hideKeyboard

internal class SearchAdapter(
    private val itemHandler: ParamsItemHandler<String>
) : RecyclerView.Adapter<SearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindItem(itemHandler)
    }

    override fun getItemCount(): Int = 1
}

internal class SearchViewHolder(
    private val binding: ItemBookSearchBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): SearchViewHolder {
            val binding = ItemBookSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return SearchViewHolder(binding)
        }
    }

    fun bindItem(itemHandler: ParamsItemHandler<String>) {
        binding.editTextId.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP) {
                itemView.context.hideKeyboard(binding.editTextId)
                itemHandler.onClick(binding.editTextId.text.toString().replace("\n", ""))
            }
            false
        }
        binding.editTextId.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                itemView.context.hideKeyboard(binding.editTextId)
                itemHandler.onClick(binding.editTextId.text.toString().replace("\n", ""))
            }
            false
        }
    }


}