package com.space.haram_android.ui.bible.intranetInfo

import com.space.haram_android.R
import com.space.haram_android.base.BaseFragment
import com.space.haram_android.databinding.FragmentIntranetInfoBinding
import com.space.haram_android.util.FragmentFactory
import com.space.haram_android.util.ViewType

class IntranetInfoFragment :
    BaseFragment<FragmentIntranetInfoBinding>(R.layout.fragment_intranet_info) {

    companion object {
        fun newInstance() = IntranetInfoFragment()
    }

    override fun init() {
        super.init()
        this.toolbarTitle = "인트라넷"
    }

    override fun initListener() {
        super.initListener()
        binding.loginButton.setOnClickListener {
            newFragmentView(FragmentFactory.createFragment(ViewType.INTRANET_LOGIN)!!)
        }
        binding.loginLater.setOnClickListener {
            requireActivity().finish()
        }
    }

}