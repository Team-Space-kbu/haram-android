package com.space.notice.ui.home


import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.BR
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.R
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.transformFragment
import com.space.notice.ui.adapter.CategoryAdapter
import com.space.notice.ui.adapter.HeaderAdapter
import com.space.notice.ui.adapter.ShimmerHomeAdapter
import com.space.notice.ui.adapter.TagRecyclerAdapter
import com.space.notice.ui.detail.NoticeDetailFragment
import com.space.notice.ui.search.NoticeSearchFragment
import com.space.shared.UiStatusType
import com.space.shared.data.notice.Notice
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
        HeaderAdapter("통합 공지사항"),
        categoryAdapter
    )

    override fun beforeObserverListener() {
        viewModel.view.observe(this) {
            when (it.uiUiStatusType) {
                UiStatusType.SUCCESS -> {
                    tagAdapter.setList(it.data!!.noticeType)
                    categoryAdapter.setList(it.data!!.notices)
                }

                UiStatusType.LOGOUT -> {
                    activity?.finishAffinity()
                    viewModel.navigatorLogin.openView(requireContext())
                }

                else -> {

                }
            }

        }
    }

    override fun initView() {
        binding.setVariable(BR.title, "공지사항")
        binding.lifecycleOwner = viewLifecycleOwner
        if (viewModel.view.isInitialized) {
            binding.recyclerView.adapter = adapter
        } else {
            binding.recyclerView.adapter = ShimmerHomeAdapter()
        }
    }

    override fun afterObserverListener() {
        viewModel.view.observe(viewLifecycleOwner) {
            if (UiStatusType.SUCCESS == it.uiUiStatusType) {
                binding.recyclerView.adapter = adapter
            }
        }
    }

}