package com.space.signup.ui.policy

import androidx.fragment.app.viewModels
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PolicyFragment : BaseFragment<FragmentEmtpyContainerBinding>(
    R.layout.fragment_emtpy_container
) {

    companion object {
        fun newInstance() = PolicyFragment()
    }

    private val viewModel: PolicyViewModel by viewModels()



}