package com.space.haram_android.ui.notice

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.space.haram_android.R
import com.space.haram_android.databinding.FragmentNoticeBinding
import com.space.haram_android.base.BaseFragment

class NoticeFragment : BaseFragment<FragmentNoticeBinding, NoticeViewModel>(R.layout.fragment_notice) {

    companion object {
        fun newInstance() = NoticeFragment()
    }

    override fun init() {
        super.init()
//        this.viewModel = ViewModelProvider(this)[NoticeViewModel::class.java]
    }
}