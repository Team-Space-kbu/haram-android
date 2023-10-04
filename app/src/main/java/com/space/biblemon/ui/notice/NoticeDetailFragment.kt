package com.space.biblemon.ui.notice

import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentNoticeDetailBinding

class NoticeDetailFragment :
    BaseFragment<FragmentNoticeDetailBinding>(R.layout.fragment_notice_detail) {

    companion object {
        fun newInstance() = NoticeDetailFragment()
    }

    private val viewModel: NoticeDetailViewModel by viewModels()



}