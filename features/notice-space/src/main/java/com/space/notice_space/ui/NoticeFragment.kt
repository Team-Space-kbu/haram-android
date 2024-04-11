package com.space.notice_space.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.view.HeaderAdapter
import com.space.core_ui.binding.adapter.view.ImageSliderAdapter
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.notice_space.ui.binding.adapter.ContentAdapter
import com.space.notice_space.ui.binding.adapter.ShimmerAdapter
import com.space.shared.UiStatusType
import com.space.shared.data.home.Notice
import com.space.shared.data.notice_space.SpaceNoticeData
import com.space.shared.decodeFromString
import com.space.shared.type.NoticeSpaceType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeFragment : ContainerFragment() {

    companion object {
        fun newInstance() = NoticeFragment()
    }

    private val type by extraNotNull<String>("type")
        .map { it.decodeFromString<SpaceNoticeData>() }
    override val viewModel: NoticeViewModel by viewModels()
    override val viewTitle: String = "공지사항"

    override fun init() {
        if (type.noticeSpaceType == NoticeSpaceType.SPACE) {
            val data by extraNotNull<String>("data")
                .map { it.decodeFromString<Notice>() }
            viewModel.noticeSpace = data
        }
        viewModel.getBible(type.seq, type.noticeSpaceType)
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = ShimmerAdapter()
    }

    override fun beforeObserverListener() {
        viewModel.view.observe(this) { result ->
            if (result.uiUiStatusType == UiStatusType.LOGOUT) {
                activity?.finishAffinity()
                viewModel.navigatorLogin.openView(requireContext())
            }
        }
    }

    override fun afterObserverListener() {
        viewModel.view.observe(this) {
            val data = it.data ?: return@observe
            val adapter = ConcatAdapter(
                HeaderAdapter(data.title),
                ImageSliderAdapter(data.imageFiles, data.imageFiles.isNotEmpty()) { image ->
                    viewModel.navigatorImage.openView(requireContext(), image)
                },
                ContentAdapter(data.content)
            )
            binding.recyclerView.adapter = adapter
        }
    }
}