package com.space.mileage.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
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
import com.space.shared.type.DividerType
import com.space.shared.type.LayoutType


@AndroidEntryPoint
class MileageFragment : ContainerFragment<MileageInfo>() {

    companion object {
        fun newInstance() = MileageFragment()
    }

    override val viewModel: MileageViewModel by viewModels()
    override val viewTitle: String = "마일리지"

    private lateinit var mileageDetails: MileageItemAdapter

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
                mileageDetails =
                    MileageItemAdapter(
                        (data.mileageDetails?.subList(0, 15) ?: emptyList()).toMutableList()
                    )
                val adapter = ConcatAdapter(
                    MileageBalanceAdapter(
                        data.mileagePayInfo ?: Mileage("", "", "")
                    ),
                    ItemHeaderAdapter(
                        title = "소비내역",
                        titleSize = 18f,
                        adapter = mileageDetails,
                        dividerType = DividerType.None,
                        layoutType = LayoutType.VERTICAL
                    )
                )
                binding.recyclerView.adapter = adapter
            }
        }
    }

    override fun initListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, state: Int) {
                if (!binding.recyclerView.canScrollVertically(1) && state == RecyclerView.SCROLL_STATE_IDLE) {
                    val data =
                        viewModel.view.value?.data?.mileageDetails ?: return@onScrollStateChanged
                    val index = mileageDetails.itemCount
                    val total = data.size
                    if (index < total) {
                        val nextIndex = (index + 15).coerceAtMost(total)
                        val subList = data.subList(index, nextIndex)
                        mileageDetails.addItem(subList)
//                        adapter.notifyItemRangeChanged(adapter.itemCount +1, subList.size)
                    }
                }
            }
        })
    }
}