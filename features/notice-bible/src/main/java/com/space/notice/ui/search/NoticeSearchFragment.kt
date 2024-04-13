package com.space.notice.ui.search

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.R
import com.space.core_ui.extraNotNull
import com.space.core_ui.logEvent
import com.space.core_ui.map
import com.space.core_ui.transformFragment
import com.space.notice.ui.adapter.CategoryAdapter
import com.space.notice.ui.adapter.ShimmerSearchAdapter
import com.space.notice.ui.detail.NoticeDetailFragment
import com.space.shared.UiStatusType
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
    private val adapter by lazy {
        CategoryAdapter(ArrayList()) { detail ->
            parentFragmentManager.transformFragment<NoticeDetailFragment>(
                R.id.container,
                "detail" to detail.encodeToString(),
                "type" to search.encodeToString()
            )
        }
    }

    override fun beforeObserverListener() {
        super.beforeObserverListener()
        viewModel.view.observe(this) {
            if (UiStatusType.SUCCESS == it.uiUiStatusType) {
                adapter.setList(it.data!!.notices)
                status = false
                if (it.data!!.start.toInt() == 1) {
                    binding.recyclerView.adapter = adapter
                }
            }
        }
    }

    override fun init() {
        super.init()
        search.let {
            viewModel.getNoticeSearch(it)
        }
        firebaseAnalytics.logEvent("bible_notice") {
            param("notice_type", search.key)
        }
    }

    override fun initView() {
        super.initView()
        if (viewModel.view.isInitialized) {
            binding.recyclerView.adapter = adapter
        } else {
            binding.recyclerView.adapter = ShimmerSearchAdapter()
        }
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