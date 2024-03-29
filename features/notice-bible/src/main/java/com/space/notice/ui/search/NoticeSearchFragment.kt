package com.space.notice.ui.search

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.BR
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.R
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.transformFragment
import com.space.notice.ui.adapter.CategoryAdapter
import com.space.notice.ui.adapter.ShimmerSearchAdapter
import com.space.notice.ui.detail.NoticeDetailFragment
import com.space.shared.data.notice.NoticeType
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeSearchFragment : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    companion object {
        fun newInstance() = NoticeSearchFragment()
    }

    private val viewModel: NoticeSearchViewModel by viewModels()
    private var status: Boolean = false

    private val search by extraNotNull<String>("search")
        .map { it.decodeFromString<NoticeType>() }

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
        viewModel.search.observe(this) {
            adapter.setList(it.notices)
            status = false
            if (it.start.toInt() == 1) {
                binding.recyclerView.adapter = adapter
            }
        }
    }

    override fun init() {
        super.init()
        search.let {
            viewModel.getNoticeSearch(it)
        }
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, "공지사항")
        binding.lifecycleOwner = viewLifecycleOwner
        if (viewModel.search.isInitialized) {
            adapter.let {
                binding.recyclerView.adapter = adapter
            }
        } else {
            binding.recyclerView.adapter = ShimmerSearchAdapter()
        }
    }

    override fun initListener() {
        super.initListener()
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!binding.recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.search.value?.let {
                        val index = it.start.toInt() + 1
                        if (index <= it.end.toInt() && !status) {
                            status = true
                            Toast.makeText(context, "더 많은 공지사항을 불러옵니다.", Toast.LENGTH_SHORT).show()
                            viewModel.getNoticeSearch(search, index.toString())
                        }
                    }
                }
            }
        })
    }

}