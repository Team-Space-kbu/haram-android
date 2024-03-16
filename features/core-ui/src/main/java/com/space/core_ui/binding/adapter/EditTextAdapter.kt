package com.space.core_ui.binding.adapter

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.EditType
import com.space.core_ui.NonParamsItemHandler
import com.space.core_ui.databinding.ItemInputEditBinding

class EditTextAdapter(
    private val string: MutableLiveData<String>,
    private val hintText: String,
    private val inputType: EditType,
    private val editorAction: Boolean = false,
    private val nonParamsItemHandler: NonParamsItemHandler = NonParamsItemHandler { }
) : RecyclerView.Adapter<EditTextViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditTextViewHolder {
        return EditTextViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: EditTextViewHolder, position: Int) =
        holder.itemBind(string, hintText, inputType, editorAction, nonParamsItemHandler)

    override fun getItemCount(): Int = 1

}

class EditTextViewHolder(
    private val binding: ItemInputEditBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): EditTextViewHolder {
            val binding =
                ItemInputEditBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return EditTextViewHolder(binding)
        }
    }

    init {
        itemView.doOnAttach {
            binding.lifecycleOwner = itemView.findViewTreeLifecycleOwner()
        }
        itemView.doOnDetach {
            binding.lifecycleOwner = null
        }
    }

    fun itemBind(
        string: MutableLiveData<String>,
        hintText: String,
        inputType: EditType,
        editorAction: Boolean,
        nonParamsItemHandler: NonParamsItemHandler
    ) {
        binding.setVariable(BR.inputText, string)
        binding.setVariable(BR.hintText, hintText)
        binding.setVariable(BR.inputType, inputType)
        if (editorAction) {
            binding.editText.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    hideKeyboard(itemView.context, binding.editText)
                    nonParamsItemHandler.onClick()
                    return@setOnKeyListener true
                }
                false
            }
            binding.editText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    hideKeyboard(itemView.context, binding.editText)
                    nonParamsItemHandler.onClick()
                    return@setOnEditorActionListener true
                }
                false
            }
        }

    }

    private fun hideKeyboard(context: Context, editText: EditText) {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)
    }
}