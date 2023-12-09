package com.space.other

import androidx.fragment.app.viewModels
import com.space.core_ui.base.BaseFragment
import com.space.other.databinding.FragmentOtherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherFragment : BaseFragment<FragmentOtherBinding>(R.layout.fragment_other) {

    companion object {
        fun newInstance() = OtherFragment()
    }

    private val viewModel: OtherViewModel by viewModels()

    override fun initView() {
        super.initView()
    }

    override fun initListener() {
        super.initListener()
    }


}