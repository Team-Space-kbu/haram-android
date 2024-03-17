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
import com.space.core_ui.binding.adapter.HeaderServiceInfoAdapter
import com.space.mileage.ui.databinding.adapter.ShimmerAdapter
import com.space.shared.UiStatusType
import com.space.shared.AuthType


@AndroidEntryPoint
class MileageFragment :
    BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    companion object {
        fun newInstance() = MileageFragment()
    }

    private val viewModel: MileageViewModel by viewModels()

    override fun beforeObserverListener() {
        super.beforeObserverListener()
        viewModel.data.observe(this) {
            if (it.uiUiStatusType == UiStatusType.REJECT) {
                viewModel.navigatorLogin.openView(requireContext(), AuthType.INTRANET)
                activity?.finish()
            }
        }
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, "마일리지")
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = ShimmerAdapter()
    }

    override fun afterObserverListener() {
        viewModel.data.observe(viewLifecycleOwner) { result ->
            if (result.uiUiStatusType == UiStatusType.SUCCESS) {
                val data = result.data!!
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