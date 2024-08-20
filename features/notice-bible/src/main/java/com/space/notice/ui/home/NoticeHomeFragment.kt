package com.space.notice.ui.home


import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.R
import com.space.core_ui.binding.adapter.FlexGrayLineDecoration
import com.space.core_ui.binding.adapter.view.EmptyRecyclerAdapter
import com.space.core_ui.binding.adapter.view.HeaderVerticalAdapter
import com.space.core_ui.extension.transformFragment
import com.space.notice.ui.adapter.CategoryAdapter
import com.space.notice.ui.adapter.ShimmerHomeAdapter
import com.space.notice.ui.adapter.TagRecyclerAdapter
import com.space.notice.ui.detail.NoticeDetailFragment
import com.space.notice.ui.search.NoticeSearchFragment
import com.space.shared.data.notice.NoticeHome
import com.space.shared.data.notice.NoticeType
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeHomeFragment : ContainerFragment<NoticeHome>() {

    companion object {
        fun newInstance() = NoticeHomeFragment()
    }

    override val viewTitle: String = "공지사항"
    override val viewModel: NoticeHomeViewModel by viewModels()
    private var adapter: RecyclerView.Adapter<*> = ShimmerHomeAdapter()

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val data = viewModel.view.value?.data ?: return
        adapter = ConcatAdapter(
            HeaderVerticalAdapter(
                "카테고리",
                18f,
                TagRecyclerAdapter(data.noticeType) { notice ->
                    parentFragmentManager.transformFragment<NoticeSearchFragment>(
                        R.id.container,
                        "search" to notice.encodeToString()
                    )
                }
            ),
            EmptyRecyclerAdapter(
                CategoryAdapter(ArrayList(data.notices)) { detail ->
                    parentFragmentManager.transformFragment<NoticeDetailFragment>(
                        R.id.container,
                        "detail" to detail.encodeToString(),
                        "type" to NoticeType("student", "학사").encodeToString()
                    )
                }
            )
        )
        binding.recyclerView.adapter = adapter
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            FlexGrayLineDecoration(
                requireContext(),
                R.drawable.vw_line_flex_divider,
                resources.getDimensionPixelSize(R.dimen.screen_margin)
            )
        )
    }

}