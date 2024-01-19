package com.space.auth.ui.intranet

import androidx.fragment.app.viewModels
import com.space.auth.R
import com.space.auth.databinding.FragmentIntranetInfoBinding
import com.space.core_ui.base.BaseFragment

class IntranetFragment : BaseFragment<FragmentIntranetInfoBinding>(R.layout.fragment_intranet_container) {

    companion object {
        fun newInstance() = IntranetFragment()
    }

    private val viewModel: IntranetViewModel by viewModels()
}