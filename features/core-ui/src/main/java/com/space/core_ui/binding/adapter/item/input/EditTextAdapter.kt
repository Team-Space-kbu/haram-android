package com.space.core_ui.binding.adapter.item.input

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.extension.EditType
import com.space.core_ui.util.NonParamsItemHandler
import com.space.core_ui.databinding.ItemInputEditBinding
import com.space.core_ui.databinding.ItemInputHighEditBinding

class EditTextAdapter(
    private val string: MutableLiveData<String>,
    private val hintText: String,
    private val inputType: EditType,
    private val high: Boolean = false,
    private val editorAction: Boolean = false,
    private val nonParamsItemHandler: NonParamsItemHandler = NonParamsItemHandler { }
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (high)
            EditTextViewHolder.highInstance(parent)
        else
            EditTextViewHolder.lowInstance(parent)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EditTextViewHolder<*>) {
            holder.itemBind(
                string,
                hintText,
                inputType,
                editorAction,
                nonParamsItemHandler
            )
        }
    }

    override fun getItemCount(): Int = 1

}

class EditTextViewHolder<T : ViewDataBinding>(
    private val binding: T
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun lowInstance(parent: ViewGroup): EditTextViewHolder<ItemInputEditBinding> {
            val binding =
                ItemInputEditBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return EditTextViewHolder(binding)
        }

        @SuppressLint("ClickableViewAccessibility")
        fun highInstance(parent: ViewGroup): EditTextViewHolder<ItemInputHighEditBinding> {
            val binding =
                ItemInputHighEditBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            binding.editText.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        binding.scroll.requestDisallowInterceptTouchEvent(true)
                        false
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        binding.scroll.requestDisallowInterceptTouchEvent(false)
                        false
                    }
                    else -> false
                }
            }
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
        binding.setVariable(BR.editorAction, editorAction)
        binding.setVariable(BR.editorActionHandler, nonParamsItemHandler)
    }

}