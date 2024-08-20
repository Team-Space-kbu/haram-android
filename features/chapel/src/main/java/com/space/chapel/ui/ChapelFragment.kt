package com.space.chapel.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.chapel.ui.databinding.adapter.ChapelDetailAdapter
import com.space.chapel.ui.databinding.adapter.ChapelInfoAdapter
import com.space.chapel.ui.databinding.adapter.ChapelFeedbackAdapter
import com.space.chapel.ui.databinding.adapter.ShimmerAdapter
import com.space.core_ui.R
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.FlexGrayLineDecoration
import com.space.core_ui.binding.adapter.view.HeaderVerticalAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.space.shared.UiStatusType
import com.space.shared.data.chapel.Chapel


@AndroidEntryPoint
class ChapelFragment : ContainerFragment<Chapel>() {
    companion object {
        fun newInstance() = ChapelFragment()
    }

    override val viewTitle: String = "채플조회"
    override val viewModel: ChapelViewModel by viewModels()

    override fun initView() {
        super.initView()
        if (viewModel.view.value?.uiUiStatusType == UiStatusType.LOADING) {
            binding.recyclerView.adapter = ShimmerAdapter()
        }
    }

    override fun beforeSuccessListener() {
        val data = viewModel.view.value?.data ?: return
        val adapter = ConcatAdapter(
            ChapelInfoAdapter(data.chapelInfo),
            ChapelFeedbackAdapter(),
            HeaderVerticalAdapter(
                "채플 상세",
                18f,
                ChapelDetailAdapter(data.chapelDetail)
            )
        )
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            FlexGrayLineDecoration(
                requireContext(),
                R.drawable.vw_line_felx_divider,
                resources.getDimensionPixelSize(R.dimen.margin_20dp)
            )
        )

    }

}