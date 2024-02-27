package com.space.notice.ui.detail

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.BR
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.R
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.notice.ui.adapter.ContentDetailAdapter
import com.space.notice.ui.adapter.HeaderDetailAdapter
import com.space.shared.data.notice.Notice
import com.space.shared.data.notice.NoticeType
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeDetailFragment :
    BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    companion object {
        fun newInstance() = NoticeDetailFragment()
    }

    private val viewModel: NoticeDetailViewModel by viewModels()

    private val type by extraNotNull<String>("type")
        .map { encodeString ->
            encodeString.decodeFromString<NoticeType>()
        }

    private val detail by extraNotNull<String>("detail")
        .map { encodeString ->
            encodeString.decodeFromString<Notice>()
        }

    override fun init() {
        super.init()
        detail.let {
            viewModel.getNoticeDetail(detail, type)
        }
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, "공지사항")
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.detail.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                HeaderDetailAdapter(it),
                ContentDetailAdapter(it)
            )
            binding.recyclerView.adapter = adapter
        }
    }
}