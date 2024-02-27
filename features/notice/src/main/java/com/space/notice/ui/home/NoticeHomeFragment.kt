package com.space.notice.ui.home


import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.BR
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.R
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.transformFragment
import com.space.notice.ui.adapter.CategoryAdapter
import com.space.notice.ui.adapter.HeaderAdapter
import com.space.notice.ui.adapter.TagRecyclerAdapter
import com.space.notice.ui.detail.NoticeDetailFragment
import com.space.notice.ui.search.NoticeSearchFragment
import com.space.shared.data.notice.NoticeType
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeHomeFragment : BaseFragment<FragmentContainerBinding>(
    R.layout.fragment_container
) {

    companion object {
        fun newInstance() = NoticeHomeFragment()
    }

    private val viewModel: NoticeHomeViewModel by viewModels()

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, "공지사항")
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.homeInfo.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                HeaderAdapter("카테고리"),
                TagRecyclerAdapter(it.noticeType) { notice ->
                    parentFragmentManager.transformFragment<NoticeSearchFragment>(
                        R.id.container,
                        "search" to notice.encodeToString()
                    )
                },
                HeaderAdapter("통합 공지사항"),
                CategoryAdapter(ArrayList(it.notices)) { detail ->
                    parentFragmentManager.transformFragment<NoticeDetailFragment>(
                        R.id.container,
                        "detail" to detail.encodeToString(),
                        "type" to NoticeType("student", "학사").encodeToString()
                    )
                }
            )
            binding.recyclerView.adapter = adapter
        }
    }

}