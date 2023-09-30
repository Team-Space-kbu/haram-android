package com.space.biblemon.ui.intranet.intranetInfo

import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentIntranetInfoBinding
import com.space.biblemon.util.FragmentFactory
import com.space.biblemon.util.ViewType

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