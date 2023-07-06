package com.space.haram_android.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

open class GeneralViewHolder<DATA>(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun <DATA> create(parent : ViewGroup, @LayoutRes layout : Int, vm : ViewModel) : GeneralViewHolder<DATA> {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), layout, parent, false).apply {
//                setVariable(BR.vm, vm)
            }
            return GeneralViewHolder(binding)
        }
    }

    open fun bind(data: DATA) {
        binding.apply {
//            setVariable(BR.data, data)
        }.run {
            executePendingBindings()
        }
    }

    open fun onViewAttachedToWindow(){
        //자식 클래스에서 구체화 한다.
    }

    open fun onViewDetachedToWindow() {
        //자식 클래스에서 구체화 한다.
    }

    open fun onViewRecycled() {
        //자식 클래스에서 구체화한다.
    }
}