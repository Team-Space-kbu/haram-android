package com.space.notice_space.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.BR
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.binding.adapter.HeaderAdapter
import com.space.core_ui.binding.adapter.ImageSliderAdapter
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.notice_space.ui.binding.adapter.ContentAdapter
import com.space.notice_space.ui.binding.adapter.ShimmerAdapter
import com.space.shared.data.home.Notice
import com.space.shared.data.notice_space.SpaceNoticeData
import com.space.shared.decodeFromString
import com.space.shared.type.NoticeSpaceType
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NoticeFragment : BaseFragment<FragmentContainerBinding>(
    R.layout.fragment_container
) {

    companion object {
        fun newInstance() = NoticeFragment()
    }

    private val type by extraNotNull<String>("type")
        .map { it.decodeFromString<SpaceNoticeData>() }
    private val viewModel: NoticeViewModel by viewModels()

    override fun init() {
        if (type.noticeSpaceType == NoticeSpaceType.SPACE) {
            val data by extraNotNull<String>("data")
                .map { it.decodeFromString<Notice>() }
            viewModel.noticeSpace = data
        }
        viewModel.getBible(type.seq, type.noticeSpaceType)
    }

    override fun initView() {
        binding.setVariable(BR.title, "공지사항")
        binding.recyclerView.adapter = ShimmerAdapter()
    }

    override fun afterObserverListener() {
        viewModel.notice.observe(this) {
            val adapter = ConcatAdapter(
                HeaderAdapter(it.title),
                ImageSliderAdapter(it.imageFiles, it.imageFiles.isNotEmpty()) { image ->
                    viewModel.navigatorImage.openView(requireContext(), image)
                },
                ContentAdapter(it.content)
            )
            binding.recyclerView.adapter = adapter
        }
    }
}