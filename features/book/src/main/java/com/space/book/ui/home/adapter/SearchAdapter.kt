package com.space.book.ui.home.adapter

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.RecyclerView
import com.space.book.R
import com.space.book.databinding.ItemBookSearchBinding

internal class SearchAdapter(
    private val itemHandler: ItemHandler
) : RecyclerView.Adapter<SearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindItem(itemHandler)
    }

    override fun getItemCount(): Int = 1

    interface ItemHandler {
        fun inputSearch(string: String)
    }
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

    fun bindItem(itemHandler: SearchAdapter.ItemHandler) {
        binding.editTextId.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP) {
                val manager =
                    binding.editTextId.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(binding.editTextId.windowToken, 0)
                val text = binding.editTextId.text.toString().replace("\n", "")
                itemHandler.inputSearch(text)
            }
            false
        }
        binding.editTextId.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val manager =
                    binding.editTextId.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(binding.editTextId.windowToken, 0)
                val text = binding.editTextId.text.toString().replace("\n", "")
                itemHandler.inputSearch(text)
            }
            false
        }
    }
}