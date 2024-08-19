package com.space.signup.ui.email.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.util.NonParamsItemHandler
import com.space.signup.BR
import com.space.signup.databinding.ItemVerifyEmailEditBinding

class EditVerifyEmailAdapter(
    private val inputText: MutableLiveData<String>,
    private val handler: NonParamsItemHandler
) : RecyclerView.Adapter<EditVerifyEmailHeaderViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EditVerifyEmailHeaderViewHolder {
        return EditVerifyEmailHeaderViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: EditVerifyEmailHeaderViewHolder, position: Int) =
        holder.itemBind(inputText, handler)

    override fun getItemCount(): Int = 1

}

class EditVerifyEmailHeaderViewHolder(
    private val binding: ItemVerifyEmailEditBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): EditVerifyEmailHeaderViewHolder {
            val binding = ItemVerifyEmailEditBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return EditVerifyEmailHeaderViewHolder(binding)
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
        handler: NonParamsItemHandler
    ) {
        binding.setVariable(BR.inputVerify, inputText)
        binding.setVariable(BR.handlerVerify, handler)
    }
}