package com.space.mileage.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.base.BaseFragment
import com.space.mileage.R
import com.space.mileage.databinding.FragmentMileageContainerBinding
import com.space.mileage.ui.databinding.adapter.HeaderAdapter
import com.space.mileage.ui.databinding.adapter.MileageBalanceAdapter
import com.space.mileage.ui.databinding.adapter.MileageItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MileageFragment :
    BaseFragment<FragmentMileageContainerBinding>(R.layout.fragment_mileage_container) {

    companion object {
        fun newInstance() = MileageFragment()
    }

    private val viewModel: MileageViewModel by viewModels()

    override fun init() {
        super.init()
    }

    override fun initView() {
        super.initView()
        binding.titleToolbar.text = "마일리지"
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