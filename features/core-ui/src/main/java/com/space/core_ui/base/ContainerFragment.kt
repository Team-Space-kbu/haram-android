package com.space.core_ui.base


import androidx.lifecycle.ViewModel
import com.space.core_ui.BR
import com.space.core_ui.R
import com.space.core_ui.databinding.FragmentContainerBinding


abstract class ContainerFragment : BaseFragment<FragmentContainerBinding>(
    R.layout.fragment_container
) {
    abstract val viewModel: ViewModel
    abstract val viewTitle: String


    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, viewTitle)
        binding.lifecycleOwner = viewLifecycleOwner
    }
}

