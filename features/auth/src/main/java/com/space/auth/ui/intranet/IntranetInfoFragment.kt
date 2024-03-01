package com.space.auth.ui.intranet

import com.space.auth.R
import com.space.auth.databinding.FragmentIntranetInfoBinding
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.startFragment
import com.space.core_ui.transformFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntranetInfoFragment : BaseFragment<FragmentIntranetInfoBinding>(
    R.layout.fragment_intranet_info
) {

    companion object {
        fun newInstance() = IntranetInfoFragment()
    }

    override fun initListener() {
        super.initListener()
        binding.loginButton.setOnClickListener {
            parentFragmentManager.startFragment<IntranetFragment>(
                com.space.core_ui.R.id.container
            )
        }
        binding.loginLater.setOnClickListener {
            activity?.finish()
        }
    }
}