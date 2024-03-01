package com.space.auth.ui.intranet

import androidx.fragment.app.viewModels
import com.space.auth.R
import com.space.auth.databinding.FragmentIntranetContainerBinding
import com.space.core_ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntranetFragment : BaseFragment<FragmentIntranetContainerBinding>(R.layout.fragment_intranet_container) {

    companion object {
        fun newInstance() = IntranetFragment()
    }

    private val viewModel: IntranetViewModel by viewModels()

    override fun initView() {
        super.initView()
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initListener() {
        super.initListener()
        binding.intBackHome.setOnClickListener {
            activity?.finish()
        }
    }

    override fun afterObserverListener() {
        viewModel.
    }

}