package com.space.mileage.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.base.ContainerFragment
import com.space.mileage.ui.databinding.adapter.HeaderAdapter
import com.space.mileage.ui.databinding.adapter.MileageBalanceAdapter
import com.space.mileage.ui.databinding.adapter.MileageItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.space.core_ui.binding.adapter.view.HeaderServiceInfoAdapter
import com.space.mileage.ui.databinding.adapter.ShimmerAdapter
import com.space.shared.UiStatusType
import com.space.shared.data.mileage.MileageInfo
import com.space.shared.type.AuthType


@AndroidEntryPoint
class MileageFragment : ContainerFragment<MileageInfo>() {

    companion object {
        fun newInstance() = MileageFragment()
    }

    override val viewModel: MileageViewModel by viewModels()
    override val viewTitle: String = "마일리지"


    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = ShimmerAdapter()
    }

    override fun afterObserverListener() {
        viewModel.view.observe(this) { result ->
            if (result.uiUiStatusType == UiStatusType.SUCCESS) {
                val data = result.data ?: return@observe
                val adapter = ConcatAdapter(
                    MileageBalanceAdapter(data.mileagePayInfo.availabilityPoint),
                    HeaderServiceInfoAdapter("마일리지 반영", "마일리지 정보가 반영되는데 일정 시간이 소요됩니다."),
                    HeaderAdapter(),
                    MileageItemAdapter(data.mileageDetails),
                )
                binding.recyclerView.adapter = adapter
            }
        }
    }
}