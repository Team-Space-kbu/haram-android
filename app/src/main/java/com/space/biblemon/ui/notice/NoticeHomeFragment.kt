package com.space.biblemon.ui.notice

import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.databinding.FragmentNoticeBinding
import com.space.biblemon.base.view.BaseFragment

class NoticeHomeFragment : BaseFragment<FragmentNoticeBinding>(R.layout.fragment_notice) {

    companion object {
        fun newInstance() = NoticeHomeFragment()
    }

    private val viewModel: NoticeHomeViewModel by viewModels()


}