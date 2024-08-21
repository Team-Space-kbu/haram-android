package com.space.mileage.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.R
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.FlexGrayLineDecoration
import com.space.core_ui.binding.adapter.view.ItemHeaderAdapter
import com.space.mileage.ui.databinding.adapter.MileageBalanceAdapter
import com.space.mileage.ui.databinding.adapter.MileageItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.space.mileage.ui.databinding.adapter.ShimmerAdapter
import com.space.shared.UiStatusType
import com.space.shared.data.mileage.Mileage
import com.space.shared.data.mileage.MileageInfo


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
        binding.recyclerView.addItemDecoration(
            FlexGrayLineDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.screen_margin)
            )
        )
    }

    override fun afterObserverListener() {
        viewModel.view.observe(this) { result ->
            if (result.uiUiStatusType == UiStatusType.SUCCESS) {
                val data = result.data ?: return@observe
                val adapter = ConcatAdapter(
                    MileageBalanceAdapter(
                        data.mileagePayInfo?: Mileage("","","")
                    ),
                    ItemHeaderAdapter(
                        "소비내역",
                        18f,
                        MileageItemAdapter(data.mileageDetails?: emptyList())
                    )
                )
                binding.recyclerView.adapter = adapter

            }
        }
    }
}