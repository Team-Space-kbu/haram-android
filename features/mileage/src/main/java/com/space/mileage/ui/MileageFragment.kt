package com.space.mileage.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.BR
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.mileage.ui.databinding.adapter.HeaderAdapter
import com.space.mileage.ui.databinding.adapter.MileageBalanceAdapter
import com.space.mileage.ui.databinding.adapter.MileageItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.space.core_ui.R


@AndroidEntryPoint
class MileageFragment :
    BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    companion object {
        fun newInstance() = MileageFragment()
    }

    private val viewModel: MileageViewModel by viewModels()

    override fun init() {
        super.init()
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title,  "마일리지")
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initListener() {
        super.initListener()
        viewModel.mileageInfo.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                MileageBalanceAdapter(it.mileagePayInfo.availabilityPoint),
                HeaderAdapter(),
                MileageItemAdapter(it.mileageDetails),
            )
            binding.recyclerView.adapter = adapter
        }

    }

}