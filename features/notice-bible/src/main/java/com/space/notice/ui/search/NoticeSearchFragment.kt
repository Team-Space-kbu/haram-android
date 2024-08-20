package com.space.notice.ui.search

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.R
import com.space.core_ui.binding.adapter.PaddingItemDecoration
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.logEvent
import com.space.core_ui.extension.map
import com.space.core_ui.extension.transformFragment
import com.space.notice.ui.adapter.CategoryAdapter
import com.space.notice.ui.adapter.ShimmerSearchAdapter
import com.space.notice.ui.detail.NoticeDetailFragment
import com.space.shared.data.notice.NoticeSearch
import com.space.shared.data.notice.NoticeType
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeSearchFragment : ContainerFragment<NoticeSearch>() {

    companion object {
        fun newInstance() = NoticeSearchFragment()
    }

    private val search by extraNotNull<String>("search")
        .map { it.decodeFromString<NoticeType>() }

    override val viewTitle: String by lazy { "${search.tag} 공지사항" }
    override val viewModel: NoticeSearchViewModel by viewModels()

    private var status: Boolean = false
    private var adapter: RecyclerView.Adapter<*> = ShimmerSearchAdapter()

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val data = viewModel.view.value?.data ?: return
        if (adapter is CategoryAdapter) {
            (adapter as CategoryAdapter).setList(data.notices)
            status = false
        } else {
            adapter = CategoryAdapter(ArrayList(data.notices)) { detail ->
                parentFragmentManager.transformFragment<NoticeDetailFragment>(
                    R.id.container,
                    "detail" to detail.encodeToString(),
                    "type" to search.encodeToString()
                )
            }
            binding.recyclerView.adapter = adapter
        }
    }

    override fun init() {
        viewModel.getNoticeSearch(search)
        firebaseAnalytics.logEvent("bible_notice") {
            param("notice_type", search.key)
        }
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            PaddingItemDecoration(
                requireContext(),
                resources.getDimensionPixelSize(R.dimen.margin_20dp),
            )
        )
    }

    override fun initListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val data = viewModel.view.value?.data ?: return
                    val index = data.start.toInt() + 1
                    if (index <= data.end.toInt() && !status) {
                        status = true
                        Toast.makeText(context, "더 많은 공지사항을 불러옵니다.", Toast.LENGTH_SHORT).show()
                        viewModel.getNoticeSearch(search, index.toString())
                    }
                }
            }
        })
    }

}