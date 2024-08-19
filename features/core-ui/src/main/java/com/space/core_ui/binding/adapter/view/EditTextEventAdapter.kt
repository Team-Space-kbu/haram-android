package com.space.core_ui.binding.adapter.view

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.extension.EditType
import com.space.core_ui.util.NonParamsItemHandler
import com.space.core_ui.databinding.ItemInputEditBinding
import com.space.core_ui.extension.hideKeyboard

class EditTextEventAdapter(
    private val string: MutableLiveData<String>,
    private val hintText: String,
    private val inputType: EditType,
    private val nonParamsItemHandler: NonParamsItemHandler
) : RecyclerView.Adapter<EditTextEventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditTextEventViewHolder {
        return EditTextEventViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: EditTextEventViewHolder, position: Int) =
        holder.itemBind(string, hintText, inputType, nonParamsItemHandler)

    override fun getItemCount(): Int = 1

}

class EditTextEventViewHolder(
    private val binding: ItemInputEditBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): EditTextEventViewHolder {
            val binding =
                ItemInputEditBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return EditTextEventViewHolder(binding)
        }
    }

    fun itemBind(
        string: MutableLiveData<String>,
        hintText: String,
        inputType: EditType,
        nonParamsItemHandler: NonParamsItemHandler
    ) {
        binding.setVariable(BR.inputText, string)
        binding.setVariable(BR.hintText, hintText)
        binding.setVariable(BR.inputType, inputType)
        binding.editText.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                itemView.context.hideKeyboard(binding.editText)
                nonParamsItemHandler.onClick()
                return@setOnKeyListener true
            }
            false
        }
        binding.editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                itemView.context.hideKeyboard(binding.editText)
                nonParamsItemHandler.onClick()
                return@setOnEditorActionListener true
            }
            false
        }
        binding.executePendingBindings()
    }

}