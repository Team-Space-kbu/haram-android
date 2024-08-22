package com.space.chapel.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.chapel.ui.databinding.adapter.ChapelDetailAdapter
import com.space.chapel.ui.databinding.adapter.ChapelInfoAdapter
import com.space.chapel.ui.databinding.adapter.ChapelFeedbackAdapter
import com.space.chapel.ui.databinding.adapter.ShimmerAdapter
import com.space.core_ui.R
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.FlexGrayLineDecoration
import com.space.core_ui.binding.adapter.view.ItemHeaderAdapter
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

    private lateinit var details: ChapelDetailAdapter


    override fun initView() {
        super.initView()
        if (viewModel.view.value?.uiUiStatusType == UiStatusType.LOADING) {
            binding.recyclerView.adapter = ShimmerAdapter()
        }
        binding.recyclerView.addItemDecoration(
            FlexGrayLineDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.screen_margin)
            )
        )
    }

    override fun beforeSuccessListener() {
        val data = viewModel.view.value?.data ?: return
        val nextIndex = 15.coerceAtMost(data.chapelDetail.size ?: 0)
        details = ChapelDetailAdapter(
            data.chapelDetail.subList(0, nextIndex).toMutableList()
        )
        val adapter = ConcatAdapter(
            ChapelInfoAdapter(data.chapelInfo),
            ChapelFeedbackAdapter(),
            ItemHeaderAdapter(
                "채플상세",
                18f,
                details
            )
        )
        binding.recyclerView.adapter = adapter

    }

    override fun initListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, state: Int) {
                if (!binding.recyclerView.canScrollVertically(1) && state == RecyclerView.SCROLL_STATE_IDLE) {
                    val data =
                        viewModel.view.value?.data?.chapelDetail ?: return@onScrollStateChanged
                    val index = details.itemCount
                    val total = data.size
                    if (index < total) {
                        val nextIndex = (index + 15).coerceAtMost(total)
                        val subList = data.subList(index, nextIndex)
                        details.addItem(subList)
                    }
                }
            }
        })
    }

}