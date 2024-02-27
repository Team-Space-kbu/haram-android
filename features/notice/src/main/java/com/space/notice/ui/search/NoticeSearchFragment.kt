package com.space.notice.ui.search

import androidx.fragment.app.viewModels
import com.space.core_ui.BR
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.R
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.shared.data.notice.NoticeType
import com.space.shared.decodeFromString
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

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title, "공지사항")
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
    }
}