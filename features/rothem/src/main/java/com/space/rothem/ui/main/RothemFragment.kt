package com.space.rothem.ui.main

import androidx.fragment.app.viewModels
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RothemFragment : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    companion object {
        fun newInstance() = RothemFragment()
    }

    private val viewModel: RothemViewModel by viewModels()

    override fun initView() {
        super.initView()
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() {
        super.afterObserverListener()

    }
}