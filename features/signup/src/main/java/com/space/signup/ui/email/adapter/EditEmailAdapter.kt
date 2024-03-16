package com.space.signup.ui.email.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.space.signup.BR
import com.space.signup.databinding.ItemEmailEditBinding

class EditEmailAdapter(
    private val inputText: MutableLiveData<String>,
    private val focus: Boolean = true
) : RecyclerView.Adapter<EditEmailHeaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditEmailHeaderViewHolder {
        return EditEmailHeaderViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: EditEmailHeaderViewHolder, position: Int) =
        holder.itemBind(inputText, focus)

    override fun getItemCount(): Int = 1

}

class EditEmailHeaderViewHolder(
    private val binding: ItemEmailEditBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): EditEmailHeaderViewHolder {
            val binding = ItemEmailEditBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return EditEmailHeaderViewHolder(binding)
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
        inputText: MutableLiveData<String>,
        focus: Boolean,
    ) {
        binding.setVariable(BR.inputText, inputText)
        binding.editText.focusable = if (focus) View.FOCUSABLE else View.NOT_FOCUSABLE

    }
}