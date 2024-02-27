package com.space.notice.ui.search

import androidx.fragment.app.viewModels
import com.space.core_ui.BR
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.R
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.transformFragment
import com.space.notice.ui.adapter.CategoryAdapter
import com.space.notice.ui.detail.NoticeDetailFragment
import com.space.shared.data.notice.Notice
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

    private val search by extraNotNull<String>("search")
        .map { encodeString ->
            encodeString.decodeFromString<NoticeType>()
        }

    private val adapter = CategoryAdapter(ArrayList()) { detail ->
        parentFragmentManager.transformFragment<NoticeDetailFragment>(
            R.id.container,
            "detail" to detail.encodeToString(),
            "type" to search.encodeToString()
        )
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
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.search.observe(viewLifecycleOwner) {
            adapter.setList(it.notices)
            binding.recyclerView.adapter = adapter
        }
    }
}