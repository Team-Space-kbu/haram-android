package com.space.core_ui.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.space.core_ui.BR

abstract class ContainerCustomFragment<VB : ViewDataBinding>(
    @LayoutRes override val layoutID: Int
) : BaseFragment<VB>(layoutID) {

    abstract val viewModel: ViewModel

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
    }
}

