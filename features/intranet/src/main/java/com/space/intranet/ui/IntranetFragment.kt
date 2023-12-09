package com.space.intranet.ui

import androidx.fragment.app.viewModels
import com.space.core_ui.base.BaseFragment
import com.space.intranet.R
import com.space.intranet.databinding.FragmentIntranetInfoBinding

class IntranetFragment : BaseFragment<FragmentIntranetInfoBinding>(R.layout.fragment_intranet_container) {

    companion object {
        fun newInstance() = IntranetFragment()
    }

    private val viewModel: IntranetViewModel by viewModels()
}