package com.space.notice.ui.home


import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.R
import com.space.core_ui.transformFragment
import com.space.notice.ui.adapter.CategoryAdapter
import com.space.notice.ui.adapter.HeaderAdapter
import com.space.notice.ui.adapter.ShimmerHomeAdapter
import com.space.notice.ui.adapter.TagRecyclerAdapter
import com.space.notice.ui.detail.NoticeDetailFragment
import com.space.notice.ui.search.NoticeSearchFragment
import com.space.shared.UiStatusType
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
    private val tagAdapter = TagRecyclerAdapter(arrayListOf()) { notice ->
        parentFragmentManager.transformFragment<NoticeSearchFragment>(
            R.id.container,
            "search" to notice.encodeToString()
        )
    }
    private val categoryAdapter = CategoryAdapter(arrayListOf()) { detail ->
        parentFragmentManager.transformFragment<NoticeDetailFragment>(
            R.id.container,
            "detail" to detail.encodeToString(),
            "type" to NoticeType("student", "학사").encodeToString()
        )
    }
    private val adapter = ConcatAdapter(
        HeaderAdapter("카테고리"),
        tagAdapter,
        categoryAdapter
    )

    override fun beforeObserverListener() {
        super.beforeObserverListener()
        viewModel.view.observe(this) {
            if (UiStatusType.SUCCESS == it.uiUiStatusType){
                tagAdapter.setList(it.data!!.noticeType)
                categoryAdapter.setList(it.data!!.notices)
            }
        }
    }

    override fun initView() {
        super.initView()
        if (viewModel.view.isInitialized) {
            binding.recyclerView.adapter = adapter
        } else {
            binding.recyclerView.adapter = ShimmerHomeAdapter()
        }
    }

    override fun afterObserverListener() {
        viewModel.view.observe(this) {
            if (UiStatusType.SUCCESS == it.uiUiStatusType) {
                binding.recyclerView.adapter = adapter
            }
        }
    }

}