package com.space.notice.ui.detail

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.notice.ui.adapter.ContentDetailAdapter
import com.space.notice.ui.adapter.HeaderDetailAdapter
import com.space.notice.ui.adapter.ShimmerDetailAdapter
import com.space.shared.UiStatusType
import com.space.shared.data.notice.Notice
import com.space.shared.data.notice.NoticeType
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeDetailFragment : ContainerFragment() {

    companion object {
        fun newInstance() = NoticeDetailFragment()
    }
    override val viewTitle: String = "공지사항"
    override val viewModel: NoticeDetailViewModel by viewModels()

    private val type by extraNotNull<String>("type")
        .map { it.decodeFromString<NoticeType>() }

    private val detail by extraNotNull<String>("detail")
        .map { it.decodeFromString<Notice>() }

    override fun init() {
        viewModel.getNoticeDetail(detail, type)
    }

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = ShimmerDetailAdapter()
    }

    override fun beforeObserverListener() {
        viewModel.view.observe(this) { result ->
            if (result.uiUiStatusType == UiStatusType.LOGOUT) {
                activity?.finishAffinity()
                viewModel.navigatorLogin.openView(requireContext())
            }
        }
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.view.observe(this) {
           if (it.uiUiStatusType == UiStatusType.SUCCESS){
               val adapter = ConcatAdapter(
                   HeaderDetailAdapter(it.data!!),
                   ContentDetailAdapter(it.data!!)
               )
               binding.recyclerView.adapter = adapter
           }
        }
    }
}