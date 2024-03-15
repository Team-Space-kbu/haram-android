package com.space.signup.ui.binding.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.space.signup.BR
import com.space.signup.databinding.ItemEditTextBinding

class EditStatusAdapter(
    private var text: String,
    private val inputStatus: MutableLiveData<Boolean>,
    @ColorInt private var color: Int? = Color.parseColor("#1A1E27")
) : RecyclerView.Adapter<EditTextHeaderViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setStatus(
        text: String,
        @ColorInt color: Int? = Color.parseColor("#1A1E27")
    ) {
        this.text = text
        this.color = color
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditTextHeaderViewHolder {
        return EditTextHeaderViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: EditTextHeaderViewHolder, position: Int) =
        holder.itemBind(text, inputStatus, color!!)

    override fun getItemCount(): Int = 1

}

class EditTextHeaderViewHolder(
    private val binding: ItemEditTextBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): EditTextHeaderViewHolder {
            val binding = ItemEditTextBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return EditTextHeaderViewHolder(binding)
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
        text: String,
        inputStatus: MutableLiveData<Boolean>,
        @ColorInt color: Int
    ) {
        binding.setVariable(BR.titleStatus, text)
        binding.setVariable(BR.inputStatus, inputStatus)
        binding.text.setTextColor(color)

    }
}