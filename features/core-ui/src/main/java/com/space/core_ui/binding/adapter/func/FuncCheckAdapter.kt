package com.space.core_ui.binding.adapter.func

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.space.core_ui.BR
import com.space.core_ui.databinding.ItemFuncCheckBinding
import com.space.shared.data.core_ui.Func

class FuncCheckAdapter<T>(
    private val info: Func<T>,
    private val status: MutableLiveData<Boolean>
) : RecyclerView.Adapter<FuncCheckViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuncCheckViewHolder {
        return FuncCheckViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: FuncCheckViewHolder, position: Int) =
        holder.itemBind(info, status)

    override fun getItemCount(): Int = 1

}

class FuncCheckViewHolder(
    private val binding: ItemFuncCheckBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun newInstance(parent: ViewGroup): FuncCheckViewHolder {
            val binding =
                ItemFuncCheckBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return FuncCheckViewHolder(binding)
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

    fun <T> itemBind(
        info: Func<T>,
        status: MutableLiveData<Boolean>
    ) {
        binding.setVariable(BR.title, info.title)
        binding.setVariable(BR.checkFunc, status)
        Glide.with(itemView.context)
            .load(info.image)
            .centerCrop()
            .into(binding.imageView)
    }
}