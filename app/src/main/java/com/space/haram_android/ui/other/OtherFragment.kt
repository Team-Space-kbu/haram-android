package com.space.haram_android.ui.other

import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentOtherBinding

class OtherFragment : BaseFragment<FragmentOtherBinding, OtherViewModel>(R.layout.fragment_other) {

    companion object {
        fun newInstance() = OtherFragment()
    }

    override fun init() {
        super.init()
//        this.viewModel = ViewModelProvider(this).get(OtherViewModel::class.java)
    }

}