package com.space.haram_android.ui.home

import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun init() {
        super.init()
//        this.viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }


}