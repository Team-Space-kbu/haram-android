package com.space.notice_space.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.binding.adapter.view.HeaderAdapter
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.notice_space.ui.binding.adapter.ContentAdapter
import com.space.notice_space.ui.binding.adapter.ShimmerAdapter
import com.space.shared.UiStatusType
import com.space.shared.data.notice_space.NoticeSpace
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeFragment : ContainerFragment<NoticeSpace>() {

    companion object {
        fun newInstance() = NoticeFragment()
    }

    private val type by extraNotNull<String>("type")
        .map { it.decodeFromString<String>() }
    override val viewModel: NoticeViewModel by viewModels()
    override val viewTitle: String = "공지사항"

    override fun init() {
        viewModel.getNotice(type)
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = ShimmerAdapter()
    }

    override fun beforeObserverListener() {
        super.beforeObserverListener()
        viewModel.view.observe(this) { result ->
            if (result.uiUiStatusType == UiStatusType.LOGOUT) {
                activity?.finishAffinity()
                viewModel.navigatorLogin.openView(requireContext())
            }
        }
    }

    override fun afterObserverListener() {
        viewModel.view.observe(this) {
            val data = it.data ?: return@observe
            val adapter = ConcatAdapter(
                HeaderAdapter(data.title ?: ""),
                ContentAdapter(data.content ?: "")
            )
            binding.recyclerView.adapter = adapter
        }
    }
}