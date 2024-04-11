package com.space.signin.ui.intranet

import com.space.core_ui.base.BaseFragment
import com.space.signin.R
import com.space.signin.databinding.FragmentIntranetInfoBinding
import com.space.core_ui.startFragment
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