package com.space.haram_android.ui.notice

import com.space.haram_android.R
import com.space.haram_android.databinding.FragmentNoticeBinding
import com.space.haram_android.base.BaseFragment

class NoticeFragment : BaseFragment<FragmentNoticeBinding>(R.layout.fragment_notice) {

    companion object {
        fun newInstance() = NoticeFragment()
    }

    override fun init() {
        super.init()
//        this.viewModel = ViewModelProvider(this)[NoticeViewModel::class.java]
    }
}